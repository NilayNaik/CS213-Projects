package sample;

/**
 * A subclass of Account.
 * A Savings account is a type of account with a 0.25% annual interest rate (0.35% for loyal accounts) and a $5 monthly fee (dependent on balance).
 * @author Evan Maggio, Nilay Naik
 */
public class Savings extends Account {
    private boolean isLoyal;            //determines whether a savings account instance is loyal or not

    /**
     * Creates a referential instance of Savings.
     * This type of account does not contain a balance or start date, but is simply meant to be compared to other accounts.
     * @param h holder of the account
     */
    public Savings (Profile h) {
        super(h);
    }

    /**
     * Creates an instance of Checking.
     * @param holder holder of the account
     * @param balance balance of the account
     * @param dateOpen date account was opened
     * @param loyal loyalty status of account
     */
    public Savings (Profile holder, double balance, Date dateOpen, boolean loyal) {
        super (holder, balance, dateOpen);
        isLoyal = loyal;
    }

    /**
     * Calculates monthly interest based on whether the account is loyal or not.
     * @see Account#monthlyInterest()
     * @return monthly interest based on a 0.35% annual interest fee if the account is loyal, and based on a 0.25% annual interest fee otherwise
     */
    public double monthlyInterest() {
        if (isLoyal){
            return getBalance() * (0.0035 /12);
        }
        return getBalance() * (0.0025 / 12);
    }

    /**
     * Calculates the monthly fee of an instance of Savings based on the balance of the account.
     * @see Account#monthlyFee()
     * @return 0 if the balance is at least $300, 5 otherwise
     */
    public double monthlyFee() {
        if (getBalance() >= 300) {
            return 0;
        }
        return 5;
    }

    /**
     * Obtain a String representation of this instance of Savings.
     * @see Account#toString()
     * @return a String representation of the account
     */
    @Override
    public String toString () {
        String ret = "*Savings*" + super.toString();
        if (isLoyal) {
            ret += "*special Savings account*";
        }
        return ret;
    }
}

