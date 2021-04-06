package application;

import java.util.ArrayList;

/**
 * An abstract representation of a sandwich
 * Defines the basic properties which every sandwich type contains
 * @author Evan Maggio, Nilay Naik
 */
public abstract class Sandwich implements Customizable{
    static final int MAX_EXTRAS = 6;		//Defines the maximum amount of extras on one sandwich as 6
    static final double PER_EXTRA = 1.99;	//Defines the price per extra on a sandwich as $1.99
    protected ArrayList <Extra> extras;		//Contains all extras added to a sandwich
    protected String [] baseIngredients;	//A list of base ingredients defined by each sandwich type

    /**
     * Creates an abstract instance of a sandwich with an empty list of base ingredients
     */
    public Sandwich () {
        extras = new ArrayList<Extra>();
        baseIngredients = new String [3];
    }

    /**
     * Determines the price of a sandwich based off of a base price and the amount of extras on the sandwich
     * @return the price of the sandwich
     */
    public abstract double price ();

    /**
     * Creates a string representation of the sandwich
     * @return <SandwichType>:<baseIngredients>;Extras:<extras>
     */
    public String toString () {
        String str = baseIngredients [0] + ',' + baseIngredients [1] + ',' + baseIngredients [2] + ";Extras:";
        if (extras.isEmpty()) return str + "none";
        int len = extras.size();
        str += extras.get(0).toString();
        for (int i = 1; i < len; i++) str += ',' + extras.get(i).toString();
        return str;
    }

    /**
     * Adds a new extra to the sandwich
     * @param obj the object which is being added, expected to be of type Extra
     * @return true if the object is of type Extra, the maximum number of extras has not been reached, and this extra is not already on the sandwich; false otherwise
     */
    public boolean add (Object obj) {
        if (obj instanceof Extra) return add ((Extra) obj);
        return false;
    }

    /**
     * Removes an extra from the sandwich
     * @param obj the object which is being added, expected to be of type Extra
     * @return true if the object is of type Extra and this extra is on the sandwich, false otherwise
     */
    public boolean remove (Object obj) {
        if (obj instanceof Extra) return extras.remove((Extra) obj);
        return false;
    }


    /**
     * A helper class to add (obj) which takes in the given object (already determined to be of type Extra) and adds it to the extras array
     * @param e the extra which is being added
     * @return true if the maximum amount of extras has not been reached and this extra is not already on the sandwich, false otherwise
     */
    private boolean add (Extra e) {
        if (extras.size() >= MAX_EXTRAS || extras.contains(e)) return false;
        extras.add(e);
        return true;
    }
}
