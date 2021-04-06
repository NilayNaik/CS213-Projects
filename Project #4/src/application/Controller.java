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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * Controller Class for the Sandwich Ordering Application.
 * Manages interactions with the application.
 * @author Nilay Naik, Evan Maggio
 */
public class Controller {

    private Order order = new Order();
    private Sandwich currentSandwich = new ChickenSandwich();
    private DecimalFormat moneyRep = new DecimalFormat ("0.00");

    @FXML
    private ComboBox<String> sandwichType;
    private ObservableList<String> sandwichChoices = FXCollections.observableArrayList("Chicken", "Beef", "Fish");

    @FXML
    private ImageView sandwichImage;
    private Image chickenImage, beefImage, fishImage;

    @FXML
    private ListView<String> basicIngredients;
    @FXML
    private ListView<Extra> extraIngredients, extraIngredientsSelected;
    private ObservableList<String> chickenBaseItems, beefBaseItems, fishBaseItems;
    private ObservableList<Extra> extraItems, extraItemsSelected;

    @FXML
    private Text sandwichPrice;

    @FXML
    private Button addSandwich, addSelectedExtra, removeSelectedExtra, orderDetailsButton;

    /**
     * Initializes the elements in the FXML and other lists and variables.
     */
    @FXML
    void initialize(){
        sandwichType.setItems(sandwichChoices);
        sandwichType.getSelectionModel().select("Chicken");
        try{
            FileInputStream inputStreamChicken = new FileInputStream("src/chickenImage.jpg");
            chickenImage = new Image(inputStreamChicken);
            FileInputStream inputStreamBeef = new FileInputStream("src/beefImage.jpg");
            beefImage = new Image(inputStreamBeef);
            FileInputStream inputStreamFish = new FileInputStream("src/fishImage.jpg");
            fishImage = new Image(inputStreamFish);
            sandwichImage.setImage(chickenImage);
        }
        catch(Exception e){
            //Error retrieving images
        }
        chickenBaseItems = FXCollections.observableArrayList(new ChickenSandwich().baseIngredients);
        beefBaseItems = FXCollections.observableArrayList(new BeefSandwich().baseIngredients);
        fishBaseItems = FXCollections.observableArrayList(new FishSandwich().baseIngredients);
        basicIngredients.setItems(chickenBaseItems);

        extraItems = FXCollections.observableArrayList(
                Extra.AVOCADO, Extra.BACON, Extra.BALSAMIC, Extra.CHEDDAR,
                Extra.CHIPOTLE_MAYO, Extra.CUCUMBER, Extra.LETTUCE, Extra.MAYO,
                Extra.MUSHROOM, Extra.PEPPERS, Extra.RED_ONION, Extra.SWISS,
                Extra.TOMATO, Extra.WHITE_ONION
        );
        extraIngredients.setItems(extraItems);
        sandwichPrice.setText("" + currentSandwich.price());

    }

    /**
     * Adds an extra topping to the sandwich.
     * @param event event that the button is pressed
     */
    @FXML
    void addExtra(ActionEvent event){
        try {
            Extra desiredExtra = extraIngredients.getSelectionModel().getSelectedItem();
            if (currentSandwich.add(desiredExtra)) {
                extraIngredientsSelected.getItems().add(desiredExtra);
                sandwichPrice.setText("" + moneyRep.format(currentSandwich.price()));
            } else {
                return;
            }
        }
        catch(Exception e){}
    }

    /**
     * Removes an extra topping from the sandwich.
     * @param event event that the button is pressed
     */
    @FXML
    void removeExtra(ActionEvent event){
        try {
            Extra selectedExtra = extraIngredientsSelected.getSelectionModel().getSelectedItem();
            if (currentSandwich.remove(selectedExtra)) {
                extraIngredientsSelected.getItems().remove(selectedExtra);
                sandwichPrice.setText("" + moneyRep.format(currentSandwich.price()));
            } else {
                return;
            }
        }
        catch(Exception e){}
    }

    /**
     * Resets selections and changes the window when a new sandwich type is chosen
     */
    @FXML
    void prepareSandwich(){
        try {
            if (sandwichType.getSelectionModel().getSelectedItem().equals("Chicken")) {
                currentSandwich = new ChickenSandwich();
                sandwichImage.setImage(chickenImage);
                basicIngredients.setItems(chickenBaseItems);
            } else if (sandwichType.getValue().equals("Beef")) {
                currentSandwich = new BeefSandwich();
                sandwichImage.setImage(beefImage);
                basicIngredients.setItems(beefBaseItems);
            } else if (sandwichType.getValue().equals("Fish")) {
                currentSandwich = new FishSandwich();
                sandwichImage.setImage(fishImage);
                basicIngredients.setItems(fishBaseItems);
            }
            extraIngredientsSelected.getItems().clear();
            sandwichPrice.setText("" + moneyRep.format(currentSandwich.price()));
        }
        catch(Exception e){}
    }

    /**
     * Adds a sandwich with the given toppings to the Order
     * @param event
     */
    @FXML
    void addSandwich(ActionEvent event){
        if (order.add(currentSandwich)){
            prepareSandwich();
        }
        else{
            return;
        }
    }

    /**
     * Opens the Order Details Window where the user can manage their Order.
     * @param event event that the button is pushed.
     */
    @FXML
    void openOrderDetails(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderDetails.fxml"));
            Parent root = loader.load();
            OrderDetailsController orderDetailsController = loader.getController();
            orderDetailsController.setOrderController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Order Details");
            stage.show();
        }
        catch (Exception e) {}
    }

    /**
     * Gets the current order.
     * @return the current order.
     */
    public Order getOrder(){
        return order;
    }

    /**
     * Sets the current order.
     * @param newOrder the new (modified) order to be used
     */
    public void setOrder(Order newOrder){
        order = newOrder;
    }

}
