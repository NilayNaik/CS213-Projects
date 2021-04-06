package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Controller Class for the Order Details Window.
 * Manages interactions with the application.
 * @author Nilay Naik, Evan Maggio
 */
public class OrderDetailsController {

    private Order order;
    private Controller orderController;
    private DecimalFormat moneyRep = new DecimalFormat ("0.00");

    @FXML
    private Button saveOrderButton, clearOrderButton, addAnotherButton, removeSandwichButton, backToOrderingButton;

    @FXML
    private Text totalPriceText;

    @FXML
    private ListView<OrderLine> orderDisplay;

    /**
     * Sets the main Order controller to retrieve order information.
     * Information is taken from the previous window.
     * @param orderController controller to obtain information from
     */
    public void setOrderController(Controller orderController){
        this.orderController = orderController;
        order = orderController.getOrder();
        displayOrder();
    }

    /**
     * Displays the order to the list view on the application.
     */
    public void displayOrder(){
        orderDisplay.getItems().clear();
        double total = 0;
        for (int i = 0; i < order.getOrderLines().size(); i++){
            orderDisplay.getItems().add(order.getOrderLines().get(i));
            total += order.getOrderLines().get(i).getPrice();
        }
        totalPriceText.setText("" + moneyRep.format(total));
    }

    @FXML
    /**
     * Writes order lines to a file
     * @param event event that button is pressed
     */
    void exportFile (ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.a
        PrintWriter pw;
        try {
            pw = new PrintWriter (targetFile);
        }
        catch (Exception e) {
            return;
        }
        Order o = order;
        int i = 1;
        String line = o.lineToString(i);
        while (! line.equals("")) {
            pw.println(line);
            i++;
            line = o.lineToString(i);
        }
        pw.flush();
        pw.close();
    }

    /**
     * Clears the current order.
     * @param event event that the button is pressed
     */
    @FXML
    void clearOrder(ActionEvent event){
        order = new Order();
        orderController.setOrder(order);
        orderDisplay.getItems().clear();
    }

    /**
     * Adds another of the selected sandwich (Order Line) to the Order.
     * @param event event that the button is pressed
     */
    @FXML
    void addAnother(ActionEvent event){
        try {
            Sandwich newSandwich = orderDisplay.getSelectionModel().getSelectedItem().getSandwich();
            order.add(newSandwich);
            displayOrder();
            orderController.setOrder(order);
        }
        catch (Exception e){}
    }

    /**
     * Removes the selected sandwich (Order Line) from the Order.
     * @param event event that the button is pressed
     */
    @FXML
    void removeSelected(ActionEvent event){
        try {
            Integer newSandwichLine = orderDisplay.getSelectionModel().getSelectedItem().getLineNumber();
            order.remove(newSandwichLine);
            displayOrder();
            orderController.setOrder(order);
        }
        catch (Exception e){}
    }

    /**
     * Return to the ordering screen.
     * @param event event that the button is pressed
     */
    @FXML
    void backToOrdering(ActionEvent event){
        orderController.setOrder(order);
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }
}
