import java.text.DecimalFormat;
/**
 * Creates an item to be added to the shopping bag.
 * Can create, compare, and format grocery items for use in the shopping bag.
 * @author Nilay Naik, Evan Maggio
 */
public class GroceryItem {
    private String name;
    private double price;
    private boolean taxable;

    /**
     * Constructs a GroceryItem with the stated name, price, and taxable status.
     * @param name of the grocery item.
     * @param price of the grocery item.
     * @param taxable boolean representing whether the item will get tax applied to it.
     */
    public GroceryItem(String name, double price, boolean taxable){
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     * Comparison between grocery items to test for equality.
     * @param obj is the grocery item to be compared to.
     * @return true if items are the same, false otherwise.
     */
    public boolean equals(Object obj){
        if (obj instanceof GroceryItem){
            GroceryItem item = (GroceryItem) obj;
            if ((name.equals(item.name)) && (price == item.price) && (taxable == item.taxable)){
                return true;
            }
        }
        return false;
    }

    /**
     * Formats the grocery item to be printed.
     * @return formatted String representing the grocery item.
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        String taxStatus = "";
        if (taxable){
            taxStatus = "is taxable";
        }
        else{
            taxStatus = "tax free";
        }
        return name + ": $" + df.format(price) + " : " + taxStatus;
    }

    /**
     * Get the name of the grocery item.
     * @return name of the grocery item.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price of the grocery item.
     * @return price of the grocery item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the taxable status of the grocery item.
     * @return boolean representing whether the item will be taxed.
     */
    public boolean isTaxable() {
        return taxable;
    }
}
