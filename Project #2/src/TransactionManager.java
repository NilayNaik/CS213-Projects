import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Handles I/O for the user to manage an Account Database.
 * Allows the user to open new Accounts, close existing Accounts, make Deposits and Withdrawals within existing accounts, and print out String representations of Accounts in the Database.
 * @author Evan Maggio, Nilay Naik
 */
public class TransactionManager {

    /**
     * Runs I/O interaction with the user to manage the database.
     */
    public void run () {
        System.out.println("Transaction processing starts.....");
        DecimalFormat moneyRep = new DecimalFormat ("0.00");        //used to format double values as money
        AccountDatabase system = new AccountDatabase ();
        Scanner sc = new Scanner (System.in);
        int type;
        while (true) {
            String c = sc.next();
            if (c.length() == 2) {
                Account account;
                switch (c.charAt(0)) {
                    case 'O':
                        type = getType (c.charAt(1));
                        if (type >= 0) {
                            account = createAccount (sc, type);
                            if (account != null) {
                                if (system.add(account)) System.out.println ("Account opened and added to the database.");
                                else existence (true);
                            }
                        }
                        else notSupported (c);
                        break;
                    case 'C':
                        type = getType (c.charAt(1));
                        if (type >= 0) {
                            account = createEchoAccount (sc, type);
                            if (account == null) invalid ();
                            else {
                                if (system.remove(account)){
                                    System.out.println ("Account closed and removed from the database.");
                                }
                                else existence (false);
                            }
                        }
                        else notSupported (c);
                        break;
                    case 'D':
                        type = getType (c.charAt(1));
                        if (type >= 0) {
                            account = createEchoAccount (sc, type);
                            if (account == null) invalid ();
                            else {
                                try {
                                    double amount = sc.nextDouble();
                                    if (system.deposit(account, amount)){
                                        System.out.println ("" + moneyRep.format(amount) + " deposited to the account.");
                                    }
                                    else existence (false);
                                } catch (Exception e) {invalid ();}
                            }
                        }
                        else notSupported (c);
                        break;
                    case 'W':
                        type = getType (c.charAt(1));
                        if (type >= 0) {
                            account = createEchoAccount (sc, type);
                            if (account == null){
                                invalid ();
                            }
                            else {
                                try {
                                    double amount = sc.nextDouble();
                                    int result = system.withdrawal (account, amount);
                                    switch (result) {
                                        case 0:
                                            System.out.println ("" + moneyRep.format(amount) + " withdrawn from the account.");
                                            break;
                                        case 1:
                                            System.out.println ("Insufficient funds.");
                                            break;
                                        case -1: existence (false);
                                    }
                                } catch (Exception e) {invalid ();}
                            }
                        }
                        break;
                    case 'P':
                        switch (c.charAt(1)) {
                            case 'A':
                                system.printAccounts();
                                break;
                            case 'D':
                                system.printByDateOpen();
                                break;
                            case 'N':
                                system.printByLastName();
                                break;
                            default: notSupported (c);
                        }
                        break;
                    default: notSupported (c);
                }
            }
            else if (c.equals("Q")) {
                System.out.println("\nTransaction processing complete.");
                sc.close();
                return;
            }
            else notSupported (c);
            sc.nextLine();
        }
    }

    /**
     * Gets the type of account (checking, savings, or money market).
     * @param c Character indicating type of account
     * @return 0 for money market, 1 for checking, 2 for savings, -1 otherwise.
     */
    private int getType (char c) {
        switch (c) {
            case 'M': return 0;
            case 'C': return 1;
            case 'S': return 2;
            default: return -1;
        }
    }

    /**
     * Creates a new referential Account subclass given parameters passed from the Scanner.
     * @param sc scanner representing user input
     * @param type type of account
     * @return a new Account if user input is properly formatted, null otherwise
     */
    private Account createEchoAccount (Scanner sc, int type) {
        Profile p;
        try {
            String first = sc.next();
            String last = sc.next();
            p = new Profile (first, last);
        } catch (Exception e) {return null;}
        switch (type) {
            case 1: return new Checking (p);
            case 2: return new Savings (p);
            case 0: return new MoneyMarket (p);
        }
        return null;
    }

    /**
     * Creates a new Account subclass given parameters passed from the Scanner.
     * @param sc scanner representing user input
     * @param type type of account
     * @return a new Account if user input is properly formatted, null otherwise
     */
    private Account createAccount (Scanner sc, int type) {
        try {
            Profile p = new Profile (sc.next(), sc.next());
            double bal = sc.nextDouble();
            String [] dateStrings = sc.next().split("/");
            if (dateStrings.length != 3) {
                invalid ();
                return null;
            }
            int month = Integer.parseInt(dateStrings [0]);
            int day = Integer.parseInt(dateStrings [1]);
            int year = Integer.parseInt(dateStrings [2]);
            Date d = new Date (year, month, day);
            if (! d.isValid()) {
                invalidDate (d);
                return null;
            }
            if (type > 0) {
                boolean b = sc.nextBoolean();
                if (type == 1) return new Checking (p, bal, d, b);
                if (type == 2) return new Savings (p, bal, d, b);
            }
            else if (type == 0) return new MoneyMarket (p, bal, d);
            return null;
        } catch (Exception e) {
            invalid ();
            return null;
        }
    }

    //Error Output
    /**
     * Prints an error statement in the case that the user enters an initial command which is not in the supported format.
     * @param command the initial command that the user entered
     */
    private void notSupported (String command) {
        System.out.println("Command '" + command + "' not supported!");
    }

    /**
     * Prints an error statement in the case that the user either attempts to open an account that already exists or references an account that does not exist.
     * @param exists represents whether the account in question exists or not
     */
    private void existence (boolean exists) {
        if (exists){
            System.out.println ("Account is already in the database.");
        }
        else {
            System.out.println ("Account does not exist.");
        }
    }

    /**
     * Prints an error statement in the case that a date's isValid () method returns false.
     * @param date the invalid date which produced this error
     */
    private void invalidDate (Date date) {
        System.out.println (date.toString() + " is not a valid date!");
    }

    /**
     * Prints an error statement in the case that any of the parameters which the user input (besides the initial command) are formatted incorrectly.
     */
    private void invalid () {
        System.out.println ("Input data type mismatch.");
    }

}
