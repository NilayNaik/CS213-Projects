import java.text.DecimalFormat;

/**
 * Container Class for various Account types
 * @author Evan Maggio, Nilay Naik
 */
public class AccountDatabase {
    private Account[] accounts; //list of accounts in database.
    private int size;           //number of accounts in database.

    /**
     * Constructs a new AccountDatabase with an initial capacity of 5.
     */
    public AccountDatabase(){
        accounts = new Account[5];
        size = 0;
    }

    /**
     * Finds the specified account in the database.
     * @param account account to be located.
     * @return index of account in the database, -1 if account is not in the database.
     */
    private int find(Account account) {
        for (int i = 0; i < size; i++){
            if (accounts[i].equals(account)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the capacity of the database by 5.
     */
    private void grow() {
        Account[] temp = accounts;
        accounts = new Account[accounts.length + 5];
        for (int i = 0; i < temp.length; i++){
            accounts[i] = temp[i];
        }
    }

    /**
     * Adds the specified Account to the database (unless it already exists).
     * @param account account to be added.
     * @return true if account added successfully, false otherwise.
     */
    public boolean add(Account account) {
        if (size >= accounts.length){
            grow();
        }
        int index = find(account);
        if (index == -1){
            accounts[size] = account;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Removes the specified Account from the database (unless it doesn't exist).
     * @param account account to be removed.
     * @return true if account removed successfully, false otherwise.
     */
    public boolean remove(Account account) {
        int index = find(account);
        if (index != -1){
            accounts[index] = accounts[size - 1];
            accounts[size - 1] = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Deposits the given amount to the specified account (if in the database).
     * @param account account to be deposited to.
     * @param amount amount to be deposited.
     * @return true if deposit successful, false otherwise.
     */
    public boolean deposit(Account account, double amount) {
        int index = find(account);
        if (index != -1){
            accounts[index].credit(amount);
            return true;
        }
        return false;
    }

    /**
     * Withdraw the listed amount from the specified account (if possible)
     * @param account account to be withdrawn from.
     * @param amount amount to be withdrawn.
     * @return 0 if withdraw successful, 1 if insufficient funds, -1 if account doesn't exist.
     */
    public int withdrawal(Account account, double amount) {
        int index = find(account);
        if (index != -1){
            if (amount > accounts[index].getBalance()){
                return 1;
            }
            else {
                if (accounts[index] instanceof MoneyMarket){
                   ((MoneyMarket)(accounts[index])).incrementWithdrawals();
                }
                accounts[index].debit(amount);
                return 0;
            }
        }
        return -1;
    }

    /**
     * Sorts the database by date of account opening (in ascending order).
     */
    private void sortByDateOpen() {
        if (size <= 1){
            return;
        }
        for (int i = 0; i < size ; i++){        //Selection Sort
            Account minimum = accounts[i];
            int minIndex = i;
            for (int j = i; j < size; j++){
                if (minimum.getDateOpen().compareTo(accounts[j].getDateOpen()) > 0){
                    minimum = accounts[j];
                    minIndex = j;
                }
            }
            Account temp = accounts[i];
            accounts[i] = minimum;
            accounts[minIndex] = temp;
        }
    }

    /**
     * Sorts the database by account holder's last name (in ascending/alphabetical order).
     */
    private void sortByLastName() {
        if (size <= 1){
            return;
        }
        for (int i = 0; i < size ; i++){        //Selection Sort
            Account minimum = accounts[i];
            int minIndex = i;
            for (int j = i; j < size; j++){
                if (minimum.getHolder().compareTo(accounts[j].getHolder()) > 0){
                    minimum = accounts[j];
                    minIndex = j;
                }
            }
            Account temp = accounts[i];
            accounts[i] = minimum;
            accounts[minIndex] = temp;
        }
    }

    /**
     * Prints the accounts in order of account opening date.
     * Also calculates the new balance of accounts considering any interest and fees.
     */
    public void printByDateOpen() {
        if (size == 0){
            System.out.println("Database is empty.");
        }
        else {
            System.out.println("\n--Printing statements by date opened--\n");
            sortByDateOpen();
            printNewBalance();
            System.out.println("--end of printing--");
        }
    }

    /**
     * Prints the accounts in order of the last names of the holders.
     * Also calculates the new balance of accounts considering any interest and fees.
     */
    public void printByLastName() {
        if (size == 0){
            System.out.println("Database is empty.");
        }
        else {
            System.out.println("\n--Printing statements by last name--\n");
            sortByLastName();
            printNewBalance();
            System.out.println("--end of printing--");
        }
    }

    /**
     * Prints the accounts in the database.
     */
    public void printAccounts() {
        if (size == 0){
            System.out.println("Database is empty.");
        }
        else {
            System.out.println("--Listing accounts in the database--");
            for (int i = 0; i < size; i++) {
                System.out.println( accounts[i].toString() );
            }
            System.out.println("--end of listing--");
        }
    }

    /**
     * Prints the new balance of all accounts considering any interest and fees.
     */
    private void printNewBalance(){
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        if (size == 0){
            System.out.println("Database is empty.");
        }
        else {
            for (int i = 0; i < size; i++) {
                System.out.println( accounts[i].toString() );
                double interest = accounts[i].monthlyInterest();
                System.out.println("-interest: $ " + df.format(interest));
                double fee = accounts[i].monthlyFee();
                System.out.println("-fees: $ " + df.format(fee));
                accounts[i].credit(interest);
                accounts[i].debit(fee);
                double newBalance = accounts[i].getBalance();
                System.out.println("-new balance: $ " + df.format(newBalance));
                if (i < size - 1){
                    System.out.println();
                }
            }
        }
    }

}
