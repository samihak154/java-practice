public class BankAccount {
    private String owner;
    private double balance;
    private boolean beenModified;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        beenModified = true;
    }

    public void displayBalance() {
        if (!beenModified) {
            System.out.println("Initial Balance (acc1): $" + balance);
        } else {
            System.out.println("Updated Balance (acc1): $" + balance);
        }
    }
}
