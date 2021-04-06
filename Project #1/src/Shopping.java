import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Handles I/O for the user to manage the shopping bag.
 * Allows users to add and remove items to or from the bag, print bag contents, checkout bag items, or quit.
 * @author Nilay Naik, Evan Maggio
 */
public class Shopping {

    /**
     * Runs the I/O interaction with the user to manage the shopping bag.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's Start Shopping!");

        ShoppingBag bag = new ShoppingBag();
        String action = "";
        String itemName = "";
        double itemPrice = 0.0;
        boolean itemTaxable = false;

        while (action != "Q") {
            String next = scanner.nextLine();
            String[] input = next.split("\\s+");
            action = input[0];
            if (action.equals("A") || action.equals("R")){
                if (input.length >= 4) {
                    itemName = input[1];
                    itemPrice = Double.parseDouble(input[2]);
                    if (itemPrice < 0.0){
                        action = "INVALID";
                    }
                    itemTaxable = Boolean.parseBoolean(input[3]);
                } else{
                    action = "INVALID";
                }
            }
            GroceryItem item = new GroceryItem(itemName, itemPrice, itemTaxable);
            switch (action) {
                case "A":
                    bag.add(item);
                    System.out.println(item.getName() + " added to the bag");
                    break;
                case "R":
                    if (bag.remove(item)){
                        System.out.println(item.getName() + " " + item.getPrice() + " removed.");
                    }
                    else {
                        System.out.println("Unable to remove, this item is not in the bag.");
                    }
                    break;
                case "P":
                    bag.print();
                    break;
                case "C":
                    if (bag.getSize()==0){
                        System.out.println("Unable to check out, the bag is empty!");
                    }
                    else{
                        checkout(bag);
                        bag = new ShoppingBag();    //empties the bag
                    }
                    break;
                case "Q":
                    if (bag.getSize() > 0){
                        checkout(bag);
                    }
                    System.out.println("Thanks for shopping with us!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    /**
     * Checks out the contents of the bags, removing them from the bag and displaying the costs.
     * @param bag in use by the user.
     */
    public void checkout(ShoppingBag bag){
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        if (bag.getSize() == 1){
            System.out.println("**Checking out " + bag.getSize() + " item:");
        }
        else {
            System.out.println("**Checking out " + bag.getSize() + " items:");
        }
        for (int i = 0; i < bag.getSize(); i++){
            System.out.println(bag.getBag()[i].toString());
        }
        double salesTotal = bag.salesPrice();
        System.out.println("*Sales total: $" + df.format(salesTotal));
        double salesTax = bag.salesTax();
        System.out.println("*Sales tax: $" + df.format(salesTax));
        double totalAmountPaid = bag.salesPrice() + bag.salesTax();
        System.out.println("*Total amount paid: $" + df.format(totalAmountPaid));
    }
}
