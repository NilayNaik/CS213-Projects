package application;

/**
 * A representation of a beef sandwich
 * Predetermines the base ingredients and base price of a beef sandwich
 * @author Evan Maggio, Nilay Naik
 */
public class BeefSandwich extends Sandwich {
    /**
     * Creates an instance of beef sandwich with base ingredients roast beef, provolone cheese, and mustard
     */
    public BeefSandwich () {
        super ();
        baseIngredients [0] = "roast beef";
        baseIngredients [1] = "provolone cheese";
        baseIngredients [2] = "mustard";
    }

    @Override
    /**
     * Determines the price of a beef sandwich with the listed extras
     * @return 10.99 + 1.99/extra
     */
    public double price() {
        return 10.99 + (extras.size() * PER_EXTRA);
    }

    @Override
    /**
     * Creates a string representation of the sandwich
     * @return a string representation of a sandwich with "beef sandwich" as SandwichType
     */
    public String toString () {
        return "beef sandwich;" + super.toString();
    }
}
