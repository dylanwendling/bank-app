import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class BankApp {
    private static Account[] accounts = new Account[3]; // An array to store Account objects

    // Main function where the program starts execution
    public static void main(String[] args) {
        try {
            // Attempt to read account information from a file
            Scanner fin = new Scanner(new File("accounts.txt"));
            int numberOfAccounts = Integer.parseInt(fin.nextLine());
            accounts = new Account[numberOfAccounts];
            
            // Populate the accounts array with Account objects from the file
            for(int i=0; i<accounts.length; i++) {
                accounts[i] = new Account();
                String accountDetails = fin.nextLine();
                String[] tokens = accountDetails.split(",");
                accounts[i].setName(tokens[0]);
                accounts[i].setBalance(Integer.parseInt(tokens[1]));
            }
            fin.close(); // Close the file
        } catch(FileNotFoundException e) {
            // Handle the case where the file is not found
            System.out.println("This application requires the file accounts.txt to be present");
            return;
        } catch(NoSuchElementException e) {
            // Handle the case where the file is corrupt
            System.out.println("Corrupt file, exiting...");
            return;
        }

        System.out.println("Bank Example Application\n");
        Scanner input = new Scanner(System.in);
        
        // Main program loop
        while(true) {
            // Display the main menu
            System.out.println("Menu:");
            System.out.println("1) get balance");
            System.out.println("2) make deposit");
            System.out.println("3) make withdrawal");
            System.out.println("4) exit (and save)");

            // Switch statement to handle user input
            switch(input.nextLine()) {
                case "1": { // Case for getting account balance
                    int accountNum = 0;
                    System.out.print("Enter account number: ");
                    try{
                        accountNum = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e) {
                        // Handle invalid input for account number
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    if(accountNum < 1 || accountNum > accounts.length) { // Check for invalid account number
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    // Display the account balance
                    System.out.printf("Account is for '%s' with a balance of $%d%n", 
                        accounts[accountNum-1].getName(),
                        accounts[accountNum-1].getBalance());
                    break;
                }
                case "2": { // Case for making a deposit
                    int accountNum = 0;
                    int amount = 0;
                    System.out.print("Enter account number: ");
                    try{
                        accountNum = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e) {
                        // Handle invalid input for account number
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    try{
                        // Get the deposit amount and ensure it is non-negative
                        System.out.print("Enter amount: ");
                        amount = Integer.parseInt(input.nextLine());
                        if(amount < 0) throw new NumberFormatException(); // Amount must be non-negative
                    } catch(NumberFormatException e) {
                        // Handle invalid input for amount
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    // Perform the deposit operation on the specified account
                    accounts[accountNum-1].deposit(amount);
                    break;
                }
                case "3": { // Case for making a withdrawal
                    int accountNum = 0;
                    int amount = 0;
                    System.out.print("Enter account number: ");
                    try{
                        accountNum = Integer.parseInt(input.nextLine());
                    } catch(NumberFormatException e) {
                        // Handle invalid input for account number
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    // Display the account holder's name
                    System.out.printf("Account is for '%s'%n", accounts[accountNum-1].getName());

                    try{
                        // Get the withdrawal amount and ensure it is non-negative
                        System.out.print("Enter amount: ");
                        amount = Integer.parseInt(input.nextLine());
                        if(amount < 0) throw new NumberFormatException(); // Amount must be non-negative
                    } catch(NumberFormatException e) {
                        // Handle invalid input for amount
                        System.out.println("Incorrect response -- please try again");
                        continue;
                    }
                    // Perform the withdrawal operation on the specified account
                    accounts[accountNum-1].withdraw(amount);
                    break;
                }
                case "4": // Case for exiting and saving data to a file
                    try {
                        // Write account information back to the file
                        PrintWriter fout = new PrintWriter(new File("accounts.txt"));
                        fout.printf("%d%n", accounts.length);
                        for(int i=0; i<accounts.length; i++) {
                            fout.println(accounts[i].serialize());
                        }
                        fout.close(); // Close the file
                    } catch(FileNotFoundException e) {
                        // Handle the case where the file cannot be written
                        System.out.println("ERROR: could not write file");
                    }
                    return; // Exit the program
                default:
                    // Handle invalid input for the main menu
                    System.out.println("Incorrect response -- please try again");
                    break;
            }
        }
    }
}