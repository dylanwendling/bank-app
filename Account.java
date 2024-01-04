public class Account {
    // Private fields to store account balance and name
    private int balance;
    private String name;

    // Getter method for retrieving the account name
    public String getName() { return name; }

    // Setter method for updating the account name
    public void setName(String newName) { name = newName; }

    // Getter method for retrieving the account balance
    public int getBalance() { return balance; }

    // Setter method for updating the account balance
    public void setBalance(int newBalance) { balance = newBalance; }

    // Method for depositing money into the account
    public void deposit(int amount) {
        // Check if the amount is non-negative
        if (amount < 0) throw new IllegalArgumentException("amount must be non-negative");
        // Update the balance by adding the deposited amount
        balance += amount;
    }

    // Method for withdrawing money from the account
    public void withdraw(int amount) {
        // Check if the amount is non-negative
        if (amount < 0) throw new IllegalArgumentException("amount must be non-negative");
        // Update the balance by subtracting the withdrawn amount
        balance -= amount;
    }

    // Method for deserializing account details from a string
    public void deserialize(String accountDetails) {
        // Split the input string into tokens using ","
        String[] tokens = accountDetails.split(",");
        // Extract the name and balance from the tokens and update the fields
        name = tokens[0];
        balance = Integer.parseInt(tokens[1]);
    }

    // Method for serializing account details into a string
    public String serialize() {
        // Combine the name and balance into a comma-separated string
        return name + "," + balance;
    }
}