import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ButtonPane extends Pane{
    private Button resetButton, addButton, removeButton, completeButton;

    public Button getResetButton() { return resetButton; }
    public Button getAddButton() { return addButton; }
    public Button getRemoveButton() { return removeButton; }
    public Button getCompleteButton() { return completeButton; }

    public ButtonPane() {
        Pane innerPane = new Pane();

        // Create the buttons
        resetButton = new Button("Reset Store");
        resetButton.setStyle("-fx-font: 12 arial;");
        resetButton.relocate(0, 0);
        resetButton.setPrefSize(140,50);

        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial;");
        addButton.relocate(250, 0);
        addButton.setPrefSize(140,50);
        addButton.setDisable(true);

        removeButton = new Button("Remove from Cart");
        removeButton.setStyle("-fx-font: 12 arial;");
        removeButton.relocate(470, 0);
        removeButton.setPrefSize(140,50);
        removeButton.setDisable(true);

        completeButton = new Button("Complete Sale");
        completeButton.setStyle("-fx-font: 12 arial;");
        completeButton.relocate(610, 0);
        completeButton.setPrefSize(140,50);
        completeButton.setDisable(true);

        // Add all three buttons to the pane
        innerPane.getChildren().addAll(resetButton, addButton, removeButton, completeButton);
        this.getChildren().addAll(innerPane);
    }
}
