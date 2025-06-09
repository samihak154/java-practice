import java.util.Scanner;

public class IO {
    private static Scanner io = new Scanner(System.in);

    public static void displayMenu(boolean lockerAvailable) {
        String menu = "What would you like to do next?\n" +
                " 1. Rent a Locker\n" +
                " 2. Access a Locker\n" +
                " 3. Release a Locker\n" +
                " Any other key to exit.\n" +
                "==================================\n" +
                "Enter your choice: ";
        String rentLocker = " 1. Rent a Locker\n";
        String rentLockerUpdated = " 1. Rent a Locker - Currently Unavailable (Lockers are full)\n";
        String fullLockersMenu = menu.replace(rentLocker, rentLockerUpdated);

        if (!lockerAvailable) {
            System.out.print(fullLockersMenu);
        } else {
            System.out.print(menu);
        }
    }

    public static String getMenuChoice() {
        String inputMenuChoice = io.nextLine();
        return inputMenuChoice;
    }

    public static int getInputLocker() {
        while (true) {
            try {
                System.out.print("Enter locker number: ");
                int inputLocker = Integer.parseInt(io.nextLine());
                return inputLocker;
            } catch (NumberFormatException ex) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    public static String getInputPin() {
        while (true) {
            System.out.print("Enter pin number: ");
            String inputPin = io.nextLine();
            if (!inputPin.matches("\\d+")) {
                System.out.println("Error: PIN must only contain numbers 0-9.");
            } else if (inputPin.length() != 4) {
                System.out.println("Error: PIN must be 4 digits.");
            } else {
                return inputPin;
            }
        }
    }

    public static boolean getConfirmation() {
        System.out.print("Are you sure? (Y/N): ");
        String inputConfirmation = io.nextLine().toLowerCase().strip();
        return inputConfirmation.equals("y") || inputConfirmation.equals("yes");
    }

    public static void displayResult(Result r) {
        System.out.println(r.getMessage());
    }
}

