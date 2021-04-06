package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Main class that sets primary stage for the transaction manager application.
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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 900, 575));
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
