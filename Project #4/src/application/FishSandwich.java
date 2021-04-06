package application;

/**
 * A representation of a fish sandwich
 * Predetermines the base ingredients and base price of a fish sandwich
 * @author Evan Maggio, Nilay Naik
 */
public class FishSandwich extends Sandwich {
    /**
     * Creates an instance of fish sandwich with base ingredients grilled snapper, cilantro, and lime
     */
    public FishSandwich () {
        super ();
        baseIngredients [0] = "grilled snapper";
        baseIngredients [1] = "cilantro";
        baseIngredients [2] = "lime";
    }

    @Override
    /**
     * Determines the price of a fish sandwich with the listed extras
     * @return 12.99 + 1.99/extra
     */
    public double price() {
        return 12.99 + (extras.size() * PER_EXTRA);
    }

    @Override
    /**
     * Creates a string representation of the sandwich
     * @return a string representation of a sandwich with "fish sandwich" as SandwichType
     */
    public String toString () {
        return "fish sandwich;" + super.toString();
    }
}

