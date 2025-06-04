import java.util.Scanner;

public class UserRegistration {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        int age = 0;
        String email = "";
        int pin = 0;

        try {
            // Age
            System.out.print("Enter your age: ");
            age = Integer.parseInt(io.nextLine());
            if (age <= 0) {
                System.out.println("Age must be positive. Please try again.");
                return;
            }
            // Email
            System.out.print("Enter your email: ");
            email = io.nextLine();
            if (email == null || email.trim().isEmpty()) {
                System.out.println("Email cannot be empty. Please try again.");
                return;
            }
            // PIN
            System.out.print("Enter your 4-digit PIN: ");
            String pinStr = io.nextLine();
            pin = Integer.parseInt(pinStr);
            if (pinStr.length() != 4) {
                System.out.println("PIN must be exactly 4 digits. Please try again.");
                return;
            }
            // Print
            System.out.println("\nRegistration Successful!");
            System.out.println("Age: " + age);
            System.out.println("Email: " + email);
            System.out.println("PIN: " + pin);
        } catch (NumberFormatException ex) {
            if (ex.getMessage().contains("For input string: \"\"")) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                System.out.println("Invalid number format. Please enter valid numbers for age and PIN.");
            }
        } finally {
            System.out.println("Registration Attempt Complete!");
            io.close();
        }
    }
}
