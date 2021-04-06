package application;

import java.util.ArrayList;

/**
 * A representation of an order
 * Contains multiple order lines
 * @author Evan Maggio, Nilay Naik
 */
public class Order implements Customizable{
    public static int lineNumber = 0;			//Represents the order number (increases for each new order)
    private ArrayList <OrderLine> orderlines;	//A list of all order lines

    /**
     * Creates a new order with a new order line
     */
    public Order () {
        lineNumber++;
        orderlines = new ArrayList <OrderLine> ();
    }

    @Override
    /**
     * Adds a new order line to the order
     * @param obj the object which is being added, expected to be of type OrderLine
     * @return true if object is of type OrderLine, false otherwise
     */
    public boolean add(Object obj) {
        if (obj instanceof Sandwich) {
            orderlines.add(new OrderLine (orderlines.size(), (Sandwich) obj));
            updateLineNumbers(orderlines.size()-1);
            return true;
        }
        return false;
    }

    @Override
    /**
     * Removes an order line from an order given the line number
     * @param obj expected to be an Integer which represents the line number of the order line which is being removed
     * @return true if object is of type Integer and the line number is valid, false otherwise
     */
    public boolean remove(Object obj) {
		if (obj instanceof Integer) {
			int ptr = ((Integer) obj).intValue() - 1;
			try {
				if (orderlines.remove(ptr) != null) {
					updateLineNumbers (ptr);
					return true;
				}
			} catch (Exception e) {}
		}
		return false;
	}

    /**
     * Sets the line number of all line numbers starting at a specific index to their index within the orderlines array list
     * @param ptr the starting index
     */
    private void updateLineNumbers (int ptr) {
        int size = orderlines.size();
        for (int i = ptr; i < size; i++) orderlines.get(i).setLineNumber(i);
    }

    /**
     * Receives the String representation of an order line
     * @param ptr the index of the order line
     * @return the String representation of the order line with line number ptr, an empty String if there is no such order line
     */
    public String lineToString (int ptr) {
        try {return orderlines.get(ptr - 1).toString();}
        catch (Exception e) {return "";}
    }

    /**
     * Returns the order lines for the current order
     * @return the array list of order lines for the current order
     */
    public ArrayList<OrderLine> getOrderLines(){
        return orderlines;
    }

}
