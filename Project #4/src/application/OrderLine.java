package application;

import java.text.DecimalFormat;

/**
 * A representation of an order line
 * Contains a sandwich and a line number
 * @author Evan Maggio, Nilay Naik
 */
public class OrderLine {
    DecimalFormat moneyRep = new DecimalFormat ("0.00");	//Used to format double values as money
    private int lineNumber;									//Represents the position of the order line
    private Sandwich sandwich;								//Sandwich which is being ordered
    private double price;									//The price of the sandwich

    /**
     * Creates an instance of orderline
     * @param n the initialized line number
     * @param s the sandwich which is being ordered
     */
    public OrderLine (int n, Sandwich s) {
        lineNumber = n;
        sandwich = s;
        price = sandwich.price();
    }

    /**
     * Resets the line number
     * @param n the new line number
     */
    public void setLineNumber (int n) {
        lineNumber = n;
    }

    /**
     * Retrieves the line number
     * @return lineNumber
     */
    public int getLineNumber () {
        return lineNumber;
    }

    /**
     * Retrieves the price of the order
     * @return price
     */
    public double getPrice () {
        return price;
    }

    /**
     * Retrieves the sandwich part of the order line
     * @return sandwich in the order line
     */
    public Sandwich getSandwich(){
        return sandwich;
    }

    /**
     * Creates a string representation of the order line
     * @return <lineNumber>.<SandwichDetails>:$<price>
     */
    public String toString () {
        return "" + lineNumber + '.' + sandwich.toString() + ":$" + moneyRep.format(price);
    }
}

