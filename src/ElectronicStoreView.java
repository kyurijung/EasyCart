import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane {
    // Initializing attributes
    private Label label1, label2, label3, label4, label5, label6, label7;
    private TextField text1, text2, text3;
    private ListView<Product> sListView, cListView, pListView;
    private ButtonPane buttonPane;

    // Get Methods
    public Label getLabel3() { return label3; }
    public ListView<Product> getStockList() { return sListView; }
    public ListView<Product> getCurrentList() { return cListView; }
    public ListView<Product> getPopularList() { return pListView; }
    public ButtonPane getButtonPane() { return buttonPane; }

    // Constructor
    public ElectronicStoreView() {
        // Create the labels
        label1 = new Label("Store Summary:");
        label1.relocate(60, 10);
        label2 = new Label("Store Stock:");
        label2.relocate(310, 10);
        label3 = new Label("Current Cart ($0.00): ");
        label3.relocate(580, 10);
        label4 = new Label("Most Popular Items:");
        label4.relocate(50, 130);
        label5 = new Label("# Sales:");
        label5.relocate(30,35);
        label6 = new Label("Revenue:");
        label6.relocate(22, 65);
        label7 = new Label("$ / Sale:");
        label7.relocate(30, 95);

        // Create the textfields
        text1 = new TextField("0");
        text1.relocate(80,30);
        text1.setPrefSize(100,20);
        text2 = new TextField("0.00");
        text2.relocate(80,60);
        text2.setPrefSize(100,20);
        text3 = new TextField("N/A");
        text3.relocate(80,90);
        text3.setPrefSize(100,20);

        // Create the lists
        sListView = new ListView<Product>();
        sListView.relocate(210, 30);
        sListView.setPrefSize(280,300);
        cListView = new ListView<Product>();
        cListView.relocate(500, 30);
        cListView.setPrefSize(280,300);
        pListView = new ListView<Product>();
        pListView.relocate(10, 150);
        pListView.setPrefSize(190,180);

        // Create the button pane
        buttonPane = new ButtonPane();
        buttonPane.relocate(30, 340);
        buttonPane.setPrefSize(750,50);

        // Add all the components to the Pane
        this.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, text1, text2, text3, sListView, cListView, pListView, buttonPane);
        this.setPrefSize(800, 400);
    }

    // Update Method
    public void update(ElectronicStore model) {
        // updating the textfields
        text1.setText(String.valueOf(model.getNumSales()));
        text2.setText(String.format("%.2f", model.getRevenue()));
        text3.setText(String.format("%.2f", model.getPricePerSale()));

        // updating the current cart total
        String cartLabel = String.format("%.2f", model.getCartTotal());
        label3.setText("Current Cart: ($" + cartLabel + ")");

        // updating the listviews
        model.getAvailableStock().clear();
        sListView.setItems(FXCollections.observableArrayList(model.getAvailableStock()));
        cListView.setItems(FXCollections.observableArrayList(model.getCurrentCart()));
        pListView.setItems(FXCollections.observableArrayList(model.getPopularList()));

        // updating when complete button is disabled
        if (cListView.getItems().size() > 0)
            buttonPane.getCompleteButton().setDisable(false);
    }
}
