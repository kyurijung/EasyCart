import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    // Initializing attributes
    private ElectronicStore model;
    private ElectronicStoreView view;

    // Constructor
    public ElectronicStoreApp() {
        model = ElectronicStore.createStore();
    }

    // All of the event handling
    public void start(Stage primaryStage) {
        // initializing a new view and adding the updates
        view = new ElectronicStoreView();
        view.update(model);

        // event for when mouse pressed on stock list (needed to access selection of items in available stock)
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                // checks if item is selected to disable add button
                if (view.getStockList().getSelectionModel().getSelectedIndex() != -1)
                    view.getButtonPane().getAddButton().setDisable(false);
            }
        });
        // event for when mouse pressed on current list (needed to access selection of items in current cart)
        view.getCurrentList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                // checks if item is selected to disable remove button
                if (view.getCurrentList().getSelectionModel().getSelectedIndex() != -1)
                    view.getButtonPane().getRemoveButton().setDisable(false);
            }
        });
        // event for when mouse pressed on ADD BUTTON
        view.getButtonPane().getAddButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.addToCart(view.getStockList().getSelectionModel().getSelectedItem());
                view.update(model);
            }
        });
        // event for when mouse pressed on REMOVE BUTTON
        view.getButtonPane().getRemoveButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.removeFromCart(view.getStockList().getSelectionModel().getSelectedItem());
                view.update(model);
            }
        });
        // event for when mouse pressed on COMPLETE BUTTON
        view.getButtonPane().getCompleteButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.completeSale();
                view.update(model);
            }
        });
        // event for when mouse pressed on RESET BUTTON
        view.getButtonPane().getResetButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.resetStore();
                view.update(model);
            }
        });
        // setting the pane and new scene
        primaryStage.setTitle("Electronic Store Application - " + ElectronicStore.createStore().getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    // Calls main method
    public static void main(String[] args) {
        launch(args);
    }
}
