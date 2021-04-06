package sample;

import java.text.DecimalFormat;

/**
 * An abstract class which defines the common features of all account types.
 * Sets the stage for the Checking, Savings, and MoneyMarket accounts.
 * @author Evan Maggio, Nilay Naik
 */
public abstract class Account {
    private Profile holder;                                                 //represents the person who in charge of the account
    private double balance;                                                 //the amount of cash held in the account
    private Date dateOpen;                                                  //the date in which the account was opened

    /**
     * Creates a referential instance of Account.
     * This type of account does not contain a balance or start date, but is simply meant to be compared to other accounts.
     * @param holder the holder of the account
     */
    public Account (Profile holder) {
        this.holder = holder;
    }

    /**
     * Creates an instance of Account
     * @param holder holder of the account
     * @param balance balance of the account
     * @param dateOpen date account was opened
     */
    public Account (Profile holder, double balance, Date dateOpen) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }

    /**
     * Subtract from the account's balance.
     * @param amount the amount for which the balance will be decreased
     */
    public void debit (double amount) {
        balance -= amount;
    }

    /**
     * Add to the account's balance.
     *  @param amount the amount for which the balance will be increased
     */
    public void credit (double amount) {
        balance += amount;
    }

    /**
     * Creates a string representation of the account.
     * @see Object#toString()
     */
    @Override
    public String toString () {
        DecimalFormat moneyRep = new DecimalFormat ("0.00");     //used to format double values as money
        return holder.toString() + "* $" + moneyRep.format(balance) + "*" + dateOpen.toString();
    }

    /**
     * Obtain the balance of the account.
     * @return the balance of the account
     */
    public double getBalance () {
        return balance;
    }

    /**
     * Obtain information regarding the holder of the account.
     * @return the holder of the account
     */
    public Profile getHolder () {
        return holder;
    }

    /**
     * Obtain the date in which the account was opened.
     * @return the opening date of the account
     */
    public Date getDateOpen () {
        return dateOpen;
    }

    /**
     * Determines whether this account is equivalent to another.
     * @param acct the account which is being compared
     * @return true if both accounts have the same holder and instances of the same subclass of Account, false otherwise
     */
    public boolean equals (Account acct) {
        return acct.getHolder().equals(holder) && this.getClass().equals(acct.getClass());
    }

    /**
     * Calculates monthly interest based on the account type.
     * @return the calculated monthly interest
     */
    public abstract double monthlyInterest ();

    /**
     * Calculates monthly fee based on the account type.
     * @return the calculated monthly fee
     */
    public abstract double monthlyFee ();

}
