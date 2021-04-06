package sample;

/**
 * A Comparable class which defines a user.
 * The user is defined by their name.
 * @author Evan Maggio, Nilay Naik
 */
public class Profile implements Comparable <Profile>{
    private String fname;                               //a String representation of the user's first name
    private String lname;                               //a String representation of the user's last name

    /**
     * Creates an instance of Profile.
     * @param first first name of profile
     * @param last last name of profile
     */
    public Profile (String first, String last) {
        this.fname = first;
        this.lname = last;
    }

    /**
     * Creates a String representation of a Profile object.
     * @see Object#toString()
     * @return a String in the format: "<first name> <last name>"
     */
    public String toString () {
        return fname + " " + lname;
    }

    /**
     * Determines whether this instance of Profile is equivalent to another.
     * @param profile the Profile which is being compared
     * @return true if both profiles have the same first and last name, false otherwise
     */
    public boolean equals (Profile profile) {
        return fname.equals(profile.fname) && lname.equals(profile.lname);
    }

    /**
     * Compares this profile to another in alphabetical order, first by last name then by first name
     * @see Comparable#compareTo(Object)
     * @param profile the Profile which is being compared
     * @return a positive value if the last name of this profile proceeds the last name of the other alphabetically, or if the last names are equivalent and the first name of this profile proceeds the first name of the other alphabetically
     * @return a negative value if the last name of the compared profile proceeds the last name of this one alphabetically, or if the last names are equivalent and the first name of the compared profile proceeds the first name of this one alphabetically
     * @return 0 if the two profile are equivalent
     */
    public int compareTo (Profile profile) {
        int c = lname.compareTo(profile.lname);
        if (c != 0){
            return c;
        }
        return fname.compareTo(profile.fname);
    }
}
