package sample;

import java.io.File;
import java.util.Scanner;

//import com.sun.tools.javac.comp.Check;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller class to modify application elements.
 * Manages all FXML elements and performs relevant operations and operation calls.
 * @author Nilay Naik, Evan Maggio
 */
public class Controller {

    @FXML
    private TextField firstNameAcc, lastNameAcc, dateOpenYearAcc, dateOpenMonthAcc, dateOpenDayAcc, balanceAcc, firstNameTrans, lastNameTrans, balanceTrans;
    @FXML
    private Button openAccount, closeAccount, clear, importFile, exportFile, deposit, withdraw;
    @FXML
    private TextArea display;
    @FXML
    private RadioButton checkingAcc, savingsAcc, moneyMarketAcc, checkingTrans, savingsTrans, moneyMarketTrans, printAccounts, printByDateOpen, printByLastName;
    @FXML
    private CheckBox directDeposit, loyalCustomer;

    private AccountDatabase database = new AccountDatabase();

    /**
     * Opens a new account and adds it to the database.
     * Uses information from relevant text fields in the application.
     * @param event event that triggers the method call.
     */
    @FXML
    void openAccount(ActionEvent event){
        try {
            if ( !(isEmpty(firstNameAcc) || isEmpty(lastNameAcc)) ) {
                Profile profile = new Profile(firstNameAcc.getText(), lastNameAcc.getText());
                Date dateOpen = null;
                double balance = -1;
                try {                                                           //checks for valid date
                    int month, day, year;
                    month = Integer.parseInt(dateOpenMonthAcc.getText());
                    day = Integer.parseInt(dateOpenDayAcc.getText());
                    year = Integer.parseInt(dateOpenYearAcc.getText());
                    dateOpen = new Date(year, month, day);
                    if (!(dateOpen.isValid())){
                        display.appendText(dateOpen.toString() + " is not a valid date!\n");
                        return;
                    }
                }
                catch (Exception e){
                    display.appendText("Please enter a valid date. (Type mismatch)\n");
                    return;
                }
                try {                                                           //checks for valid balance
                    balance = Double.parseDouble(balanceAcc.getText());
                    if (balance < 0){
                        display.appendText("Please enter a valid (non-negative) account balance.\n");
                        return;
                    }
                }
                catch (Exception e){
                    display.appendText("Please enter a valid account balance. (Type mismatch)\n");
                    return;
                }
                if (checkingAcc.isSelected()) {
                    Account account = new Checking(profile, balance, dateOpen, directDeposit.isSelected());
                    if (database.add(account)){
                        display.appendText("Account opened and added to the database.\n");
                    }
                    else {
                        display.appendText("Account is already in the database.\n");
                    }
                } else if (savingsAcc.isSelected()) {
                    Account account = new Savings(profile, balance, dateOpen, loyalCustomer.isSelected());
                    if (database.add(account)){
                        display.appendText("Account opened and added to the database.\n");
                    }
                    else {
                        display.appendText("Account is already in the database.\n");
                    }
                } else if (moneyMarketAcc.isSelected()) {
                    Account account = new MoneyMarket(profile, balance, dateOpen);
                    if (database.add(account)){
                        display.appendText("Account opened and added to the database.\n");
                    }
                    else {
                        display.appendText("Account is already in the database.\n");
                    }
                } else {
                    display.appendText("Please select account type.\n");
                }
            }
            else {
                display.appendText("Please enter a first and last name.\n");
            }
        }
        catch (Exception e){
            display.appendText("Error with account, invalid input.\n");
        }
    }

    /**
     * Closes an account and removes it from the database.
     * Uses information from relevant text fields to identify the desired account.
     * @param event event that triggers the method call.
     */
    @FXML
    void closeAccount(ActionEvent event){
        try {
            if ( !(isEmpty(firstNameAcc) || isEmpty(lastNameAcc)) ) {
                Profile profile = new Profile(firstNameAcc.getText(), lastNameAcc.getText());
                if (checkingAcc.isSelected()) {
                    Account account = new Checking(profile);
                    if (database.remove(account)){
                        display.appendText("Account closed and removed from the database.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (savingsAcc.isSelected()) {
                    Account account = new Savings(profile);
                    if (database.remove(account)){
                        display.appendText("Account closed and removed from the database.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (moneyMarketAcc.isSelected()) {
                    Account account = new MoneyMarket(profile);
                    if (database.remove(account)){
                        display.appendText("Account closed and removed from the database.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else {
                    display.appendText("Please select account type.\n");
                }
            }
            else {
                display.appendText("Please enter a first and last name.\n");
            }
        }
        catch (Exception e){
            display.appendText("Error with account, invalid input.\n");
        }
    }

    /**
     * Clears the display of the application.
     * @param event event that triggers the method call.
     */
    @FXML
    void clearScreen(ActionEvent event){
        display.setText("");
    }

    /**
     * Enables/Disables relevant checkboxes based on selected account type.
     * @param event event that triggers the method call.
     */
    @FXML
    void accountTypeListenerAcc(ActionEvent event){
        if (checkingAcc.isSelected()){
            directDeposit.setDisable(false);
            directDeposit.setSelected(false);
            loyalCustomer.setDisable(true);
            loyalCustomer.setSelected(false);
        }
        else if (savingsAcc.isSelected()){
            directDeposit.setDisable(true);
            directDeposit.setSelected(false);
            loyalCustomer.setDisable(false);
            loyalCustomer.setSelected(false);
        }
        else if (moneyMarketAcc.isSelected()){
            directDeposit.setDisable(true);
            directDeposit.setSelected(false);
            loyalCustomer.setDisable(true);
            loyalCustomer.setSelected(false);
        }
    }

    /**
     * Checks whether the given text field is empty.
     * @param textField text field to be checked.
     * @return true if text field is empty, false otherwise.
     */
    private boolean isEmpty(TextField textField){
        if (textField.getText().equals("")){
            return true;
        }
        return false;
    }

    /**
     * Checks whether the data entered into the balance field of the add/remove account tab is an integer.
     * @param event event that triggers the method call.
     */
    @FXML
    void checkIntegerAcc(KeyEvent event) {
        try {
            int integer = Integer.parseInt(balanceAcc.getText());
        }
        catch (NumberFormatException e) {
            display.appendText("Please enter an integer.\n");
        }
    }

    /**
     * Checks whether the data entered into the balance field of the deposit/withdraw funds tab is an integer.
     * @param event event that triggers the method call.
     */
    @FXML
    void checkIntegerTrans(KeyEvent event) {
        //messageArea.clear();
        try {
            int integer = Integer.parseInt(balanceTrans.getText());
        }
        //Show the error message in the TextArea.
        catch (NumberFormatException e) {
            display.appendText("Please enter an integer.\n");
        }
    }

    /**
     * Deposits an amount into the specified account in the database.
     * Account and balance to be added is taken from the relevant text fields.
     * @param event event that triggers the method call.
     */
    @FXML
    void deposit(KeyEvent event){
        try {
            if ( !(isEmpty(firstNameTrans) || isEmpty(lastNameTrans)) ){
                Profile profile = new Profile(firstNameTrans.getText(), lastNameTrans.getText());
                double balance = -1;
                try {
                    balance = Double.parseDouble(balanceTrans.getText());
                    if (balance < 0){
                        display.appendText("Please enter a valid (non-negative) account balance.\n");
                        return;
                    }
                }
                catch (Exception e){
                    display.appendText("Please enter a valid balance. (Type mismatch)\n");
                    return;
                }
                if (checkingTrans.isSelected()) {
                    Account account = new Checking(profile);
                    if (database.deposit(account, balance)){
                        display.appendText(balance + " deposited to account.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (savingsTrans.isSelected()) {
                    Account account = new Savings(profile);
                    if (database.deposit(account, balance)){
                        display.appendText(balance + " deposited to account.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (moneyMarketTrans.isSelected()) {
                    Account account = new MoneyMarket(profile);
                    if (database.deposit(account, balance)){
                        display.appendText(balance + " deposited to account.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else {
                    display.appendText("Please select account type.\n");
                }
            }
            else {
                display.appendText("Please enter a first and last name.\n");
            }
        }
        catch (Exception e){
            display.appendText("Invalid deposit.");
        }
    }

    /**
     * Withdraws money from an account in the database.
     * Account and balance to be withdrawn is taken from the relevant text fields.
     * @param event event that triggers the method call.
     */
    @FXML
    void withdraw(KeyEvent event){
        try {
            if ( !(isEmpty(firstNameTrans) || isEmpty(lastNameTrans)) ){
                Profile profile = new Profile(firstNameTrans.getText(), lastNameTrans.getText());
                double balance = -1;
                try {
                    balance = Double.parseDouble(balanceTrans.getText());
                    if (balance < 0){
                        display.appendText("Please enter a valid (non-negative) account balance.\n");
                        return;
                    }
                }
                catch (Exception e){
                    display.appendText("Please enter a valid balance. (Type mismatch)\n");
                    return;
                }
                if (checkingTrans.isSelected()) {
                    Account account = new Checking(profile);
                    if (database.withdrawal(account, balance) == 0){
                        display.appendText(balance + " withdrawn from account.\n");
                    }
                    else if (database.withdrawal(account, balance) == 1){
                        display.appendText("Insufficient Funds.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (savingsTrans.isSelected()) {
                    Account account = new Savings(profile);
                    if (database.withdrawal(account, balance) == 0){
                        display.appendText(balance + " withdrawn from account.\n");
                    }
                    else if (database.withdrawal(account, balance) == 1){
                        display.appendText("Insufficient Funds.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else if (moneyMarketTrans.isSelected()) {
                    Account account = new MoneyMarket(profile);
                    if (database.withdrawal(account, balance) == 0){
                        display.appendText(balance + " withdrawn from account.\n");
                    }
                    else if (database.withdrawal(account, balance) == 1){
                        display.appendText("Insufficient Funds.\n");
                    }
                    else {
                        display.appendText("Account does not exist.\n");
                    }
                } else {
                    display.appendText("Please select account type.\n");
                }
            }
            else {
                display.appendText("Please enter a first and last name.\n");
            }
        }
        catch (Exception e){
            display.appendText("Invalid withdrawal.");
        }
    }

    /**
     * Prints the contents of the database to the display / text area in the application.
     * Relevant radio buttons select which type of print operation will take place.
     * @param event event that triggers the method call.
     */
    @FXML
    void printDatabase(ActionEvent event){
        try{
            if (printAccounts.isSelected()){
                display.appendText(database.printAccounts());
            }
            else if (printByDateOpen.isSelected()){
                display.appendText(database.printByDateOpen());
            }
            else if (printByLastName.isSelected()){
                display.appendText(database.printByLastName());
            }
            else {
                display.appendText("Please select print type.\n");
            }
        }
        catch (Exception e){
            display.appendText("Invalid print.\n");
        }
    }

    /**
     * Imports a file to be used as the database.
     * @param event event that triggers the method call.
     */
    @FXML
    void importFile(ActionEvent event) {
        display.clear();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        Scanner sc;
        try {
            sc = new Scanner (sourceFile);
        }catch (Exception e) {
            display.appendText("File not found.");
            return;
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String [] args0 = line.split(",");
            if (args0.length != 6) {
                invalid();
                continue;
            }
            int t = -1;
            if (args0 [0].equals("C")) t = 1;
            else if (args0 [0].equals("S")) t = 2;
            else if (args0 [0].equals("M")) t = 0;
            String [] args1 = new String [7];
            args1 [0] = args0 [1];
            args1 [1] = args0 [2];
            args1 [2] = args0 [3];
            String [] date = args0 [4].split("/");
            if (date.length != 3) {
                invalid();
                continue;
            }
            args1 [3] = date [0];
            args1 [4] = date [1];
            args1 [5] = date [2];
            args1 [6] = args0 [5];
            Account a = createAccount (args1, t);
            if (a != null) {
                //if (system.add(a)) display.appendText("Account opened and added to the database.\n");
                //else existence (true);
            }
        }
        sc.close();
    }

    /**
     * Creates an account to help with file importing.
     * @param args arguments for account
     * @param t type of account.
     * @return account that was created.
     */
    private Account createAccount (String [] args, int t) {
        try {
            Profile p = new Profile (args [0], args [1]);
            double bal = Double.parseDouble(args [2]);
            int month = Integer.parseInt(args [3]);
            int day = Integer.parseInt(args [4]);
            int year = Integer.parseInt(args [5]);
            Date d = new Date (year, month, day);
            if (! d.isValid()) {
                //invalidDate(d);
                return null;
            }
            if (t == 1) return new Checking (p, bal, d, Boolean.parseBoolean(args [6]));
            else if (t == 2) return new Savings (p, bal, d, Boolean.parseBoolean(args [6]));
            else if (t == 0) return new MoneyMarket (p, bal, d);
            else {
                invalid();
                return null;
            }
        } catch (Exception e) {
            invalid();
            return null;
        }
    }

    private void invalid () {
        display.appendText("Input data type mismatch.");
    }


    /**
     * Exports the database to a text file.
     * @param event event that triggers the method call.
     */
    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.
    }

}
