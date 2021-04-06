package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * Main class that sets primary stage for the sandwich order application.
 * Creates the window and displays it to the screen.
 * @author Nilay Naik, Evan Maggio
 */
public class Main extends Application {

    /**
     * Stars the application by loading the root window.
     * @param primaryStage primary stage of the application.
     * @throws Exception for runtime errors.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OrderMenu.fxml"));
        primaryStage.setTitle("Sandwich Ordering");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    /**
     * Launches the transaction manager application.
     * @param args arguments sent with method call.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
