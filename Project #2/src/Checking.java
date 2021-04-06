/**
 * A subclass of Account.
 * A Checking account is a type of account with a 0.05% annual interest rate and a $25 monthly fee (dependent on balance and whether the account has direct deposit).
 * @author Evan Maggio, Nilay Naik
 */
public class Checking extends Account {
    private boolean directDeposit;          //determines whether a checking account instance has direct deposit

    /**
     * Creates a referential instance of Checking
     * This type of account does not contain a balance or start date, but is simply meant to be compared to other accounts
     * @param holder the holder of the account
     */
    public Checking (Profile holder) {
        super(holder);
    }

    /**
     * Creates an instance of Checking
     * @param holder holder of the account
     * @param balance balance in the account
     * @param dateOpen date account was opened
     * @param directDeposit indicates whether the account is direct deposit
     */
    public Checking (Profile holder, double balance, Date dateOpen, boolean directDeposit) {
        super (holder, balance, dateOpen);
        this.directDeposit = directDeposit;
    }

    /**
     * Calculates monthly interest based on a 0.05% annual interest rate.
     * @see Account#monthlyInterest()
     * @return the calculated monthly interest
     */
    public double monthlyInterest() {
        return getBalance() * (0.0005 / 12);
    }

    /**
     * Calculates the monthly fee of an instance of Checking based on the balance of the account and whether it is a direct deposit account.
     * @see Account#monthlyFee()
     * @return 0 if this is a direct deposit or the balance is at least $1500, 25 otherwise
     */
    public double monthlyFee() {
        if (directDeposit || getBalance() >= 1500){
            return 0.0;
        }
        return 25.0;
    }

    /**
     * Obtain a String representation of this instance of Checking.
     * @see Account#toString()
     * @return a String representation of the account
     */
    @Override
    public String toString () {
        String ret = "*Checking*" + super.toString();
        if (directDeposit){
            ret += "*direct deposit account*";
        }
        return ret;
    }
}

