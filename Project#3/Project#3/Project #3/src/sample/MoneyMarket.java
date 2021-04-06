package sample;

/**
 * A subclass of Account
 * A MoneyMarket account is a type of account with a 0.65% annual interest rate and a $12 monthly fee (dependent on balance and number of withdrawals)
 * @author Evan Maggio, Nilay Naik
 */
public class MoneyMarket extends Account{
    private int withdrawals;                //the amount of withdrawals taken out of the account

    /**
     * Creates a referential instance of MoneyMarket.
     * This type of account does not contain a balance or start date, but is simply meant to be compared to other accounts.
     * @param holder set the holder of the account
     */
    public MoneyMarket (Profile holder) {
        super(holder);
    }

    /**
     * Creates an instance of MoneyMarket
     * @param holder holder of the account
     * @param balance balance in the account
     * @param dateOpen data account was opened
     */
    public MoneyMarket (Profile holder, double balance, Date dateOpen) {
        super (holder, balance, dateOpen);
        withdrawals = 0;
    }

    /**
     * Calculates monthly interest based on a 0.65% annual interest rate
     * @see Account#monthlyInterest()
     * @return the calculated monthly interest
     */
    public double monthlyInterest() {
        return getBalance() * (0.0065 / 12);
    }

    /**
     * Calculates the monthly fee of an instance of MoneyMarket based on the balance of the account and the number of withdrawals taken out
     * @see Account#monthlyFee()
     * @return 12 if balance is under $2500 and withdrawals exceeds 6, 0 otherwise
     */
    public double monthlyFee() {
        if (getBalance() < 2500 || withdrawals > 6){
            return 12;
        }
        return 0;
    }

    /**
     * Obtain a String representation of this instance of MoneyMarket
     * @see Account#toString()
     * @return a String representation of the account
     */
    @Override
    public String toString () {
        String ret = "*MoneyMarket*" + super.toString() + "*" + withdrawals + " withdrawal";
        if (withdrawals != 1){
            ret += "s";
        }
        return ret + "*";
    }

    /**
     * Increments the counter of withdrawals from the account by 1.
     */
    public void incrementWithdrawals(){
        withdrawals++;
    }

}
