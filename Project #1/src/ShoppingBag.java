/**
 * Creates a shopping bag and handles shopping bag operations.
 * Can modify the contents of the bag and performs necessary calculations and operations related to it.
 * @author Nilay Naik, Evan Maggio
 */
public class ShoppingBag {
    private GroceryItem[] bag; // array-based implementation of the bag
    private int size; // number of items currently in the bag

    /**
     * Testbed main for testing add(), remove(), grow(), and salesTax() methods.
     * Shows output for test cases described in the test specification document.
     * @param args sent with program call.
     */
    public static void main (String[] args) {
        System.out.println("Test Case 1: ");    //tests add() while bag is not full
        ShoppingBag addTest = new ShoppingBag();
        addTest.add(new GroceryItem("addedItem", 9.00, true));
        addTest.print();

        System.out.println("Test Case 2: ");    //test add() while bag is full.
        ShoppingBag addTest2 = new ShoppingBag();
        addTest2.add(new GroceryItem("addedItem1", 1.99, false));
        addTest2.add(new GroceryItem("addedItem2", 1.99, false));
        addTest2.add(new GroceryItem("addedItem3", 1.99, false));
        addTest2.add(new GroceryItem("addedItem4", 1.99, false));
        addTest2.add(new GroceryItem("addedItem5", 1.99, false));
        addTest2.add(new GroceryItem("addedItem6", 1.99, false));
        addTest2.print();

        System.out.println("Test Case 3: ");    //tests remove() when item is in bag.
        ShoppingBag removeTest1 = new ShoppingBag();
        removeTest1.add(new GroceryItem("removableItem", 2.99, false));
        System.out.println(removeTest1.remove(new GroceryItem("removableItem", 2.99, false)));

        System.out.println("Test Case 4: ");    //test remove() when item is not in bag.
        ShoppingBag removeTest2 = new ShoppingBag();
        System.out.println(removeTest2.remove(new GroceryItem("removableItem", 2.99, false)));

        System.out.println("Test Case 5: ");    //tests grow() to see if bag grows in size.
        ShoppingBag growTest = new ShoppingBag();
        System.out.println("Capacity before: " + growTest.getBag().length);
        growTest.grow();
        System.out.println("Capacity after: " + growTest.getBag().length);

        System.out.println("Test Case 6: ");    //tests salesTax() to check tax calculation for taxable items.
        ShoppingBag salesTaxTest = new ShoppingBag();
        salesTaxTest.add(new GroceryItem("taxedItem1", 200.00, true));
        salesTaxTest.add(new GroceryItem("taxedItem2", 200.00, true));
        salesTaxTest.add(new GroceryItem("taxedItem3", 200.00, true));
        salesTaxTest.add(new GroceryItem("taxedItem4", 200.00, true));
        salesTaxTest.add(new GroceryItem("taxedItem5", 200.00, true));
        System.out.println("Sales tax: $" + salesTaxTest.salesTax());
    }

    /**
     * Constructs an empty shopping bag with an initial capacity of 5 grocery items.
     */
    public ShoppingBag() {
        bag = new GroceryItem[5];
        size = 0;
    }

    /**
     * Locates the specified GroceryItem in the shopping bag.
     * @param item to be located in the bag.
     * @return index of item in the bag, -1 if item is not in the bag.
     */
    private int find(GroceryItem item) {
        for (int i = 0 ; i < size ; i++){
            if (bag[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases capacity of the shopping bag by 5.
     */
    private void grow() {
        GroceryItem[] temp = bag;
        bag = new GroceryItem[bag.length+5];
        for (int i = 0 ; i < temp.length ; i++){
            bag[i] = temp[i];
        }
    }

    /**
     * Adds a GroceryItem to the shopping bag.
     * @param item to be added to the bag.
     */
    public void add(GroceryItem item) {
        if (size >= bag.length){
            grow();
        }
        bag[size] = item;
        size++;
    }

    /**
     * Removes a GroceryItem from the shopping bag.
     * @param item to be removed.
     * @return true if item was successfully removed, false otherwise.
     */
    public boolean remove(GroceryItem item) {
        int index = find(item);
        if (index != -1) {
            bag[index] = bag[size - 1];
            bag[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Calculates the sales price/sum of all items in the bag without considering tax.
     * @return total price of all items in the bag.
     */
    public double salesPrice() {
        double total = 0.0;
        for (int i = 0; i < size; i++){
            total += bag[i].getPrice();
        }
        return total;
    }

    /**
     * Calculates the sales tax for all taxable items in the bag.
     * @return total tax cost of all items in the bag.
     */
    public double salesTax() {
        double tax = 0.0;
        for (int i = 0; i < size; i++){
            if (bag[i].isTaxable()){
                tax += bag[i].getPrice() * 0.06625;
            }
        }
        return tax;
    }

    /**
     * Prints a list of all items currently in the shopping bag.
     */
    public void print() {
        if (size == 0){
            System.out.println("The bag is empty!");
        }
        else {
            if (size == 1){
                System.out.println("**You have " + size + " item in the bag: ");
            }
            else {
                System.out.println("**You have " + size + " items in the bag: ");
            }
            for (int i = 0; i < size; i++){
                System.out.println(bag[i].toString());
            }
            System.out.println("**End of list");
        }
    }

    /**
     * Get the current shopping bag.
     * @return current shopping bag.
     */
    public GroceryItem[] getBag() {
        return bag;
    }

    /**
     * Gets the size of/number of items in the bag.
     * @return size of/number of items in the bag.
     */
    public int getSize() {
        return size;
    }
}
