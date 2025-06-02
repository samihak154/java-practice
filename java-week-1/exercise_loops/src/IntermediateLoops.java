import java.util.Scanner;

public class IntermediateLoops {
    public static void main(String[] args) {
        // Multiplication Table (For Loop)
        Scanner io = new Scanner(System.in);
        System.out.print("Please enter a number (1-20): ");
        int number = Integer.parseInt(io.nextLine());
        System.out.println("\nMultiplication table: ");
        for (int i = 1; i <= 10; i++){
            System.out.println(number + " x " + i + " = " + number*i);
        }

        // Password Validator (While Loop)
        String password = "letmein";
        String userPassword = "";
        while (!userPassword.equals(password)) {
            System.out.print("\nPlease enter the password: ");
            userPassword = io.nextLine();
            if (!userPassword.equals(password)) {
                System.out.println("Incorrect password, please try again: ");
            }
        }
        System.out.println("Correct!");

        // Find the First Vowel (For Loop)
        System.out.print("\nPlease enter a word: ");
        String word = io.nextLine().toLowerCase();
        boolean vowelFound = false;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i'
                    || currentChar == 'o' || currentChar == 'u') {
                System.out.println("First vowel '" + currentChar + "' found at position: " + (i + 1));
                vowelFound = true;
                break;
            }
        }
        if (!vowelFound) {
            System.out.println("No vowels found in the word.");
        }

        // Simple ATM System (Do-While Loop)
        int accountBalance = 500;
        System.out.println("\n=====ATM System=====");
        String userChoice = "";
        do {
            System.out.println("Would you like to: \n" +
                    "1. Withdraw\n" +
                    "2. Deposit\n" +
                    "3. Check Balance\n" +
                    "4. Exit");
            System.out.print("Please enter your choice: ");
            userChoice = io.nextLine();
            switch (userChoice) {
                case "1":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(io.nextLine());
                    if (withdrawAmount > accountBalance) {
                        System.out.println("Insufficient funds!\n");
                    } else if (withdrawAmount <= 0) {
                        System.out.println("Amount must be positive!\n");
                    } else {
                        accountBalance -= withdrawAmount;
                        System.out.println("Withdrawal successful!\nNew balance: $" + accountBalance + "\n");
                    }
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(io.nextLine());
                    if (depositAmount <= 0) {
                        System.out.println("Amount must be positive!\n");
                    } else {
                        accountBalance += depositAmount;
                        System.out.println("Deposit successful.\nNew balance: $" + accountBalance + "\n");
                    }
                    break;
                case "3":
                    System.out.println("Current balance: $" + accountBalance + "\n");
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (!userChoice.equals("4"));
    }
}
