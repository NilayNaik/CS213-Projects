package application;

import java.text.DecimalFormat;

/**
 * A representation of a chicken sandwich
 * Predetermines the base ingredients and base price of a chicken sandwich
 * @author Evan Maggio, Nilay Naik
 */
public class ChickenSandwich extends Sandwich{
    /**
     * Creates an instance of chicken sandwich with base ingredients fried chicken, spicy sauce, and pickles
     */
    public ChickenSandwich () {
        super ();
        baseIngredients [0] = "fried chicken";
        baseIngredients [1] = "spicy sauce";
        baseIngredients [2] = "pickles";
    }

    @Override
    /**
     * Determines the price of a chicken sandwich with the listed extras
     * @return 8.99 + 1.99/extra
     */
    public double price() {
        DecimalFormat df = new DecimalFormat();

        return 8.99 + (extras.size() * PER_EXTRA);
    }

    @Override
    /**
     * Creates a string representation of the sandwich
     * @return a string representation of a sandwich with "chicken sandwich" as SandwichType
     */
    public String toString () {
        return "chicken sandwich;" + super.toString();
    }
}

