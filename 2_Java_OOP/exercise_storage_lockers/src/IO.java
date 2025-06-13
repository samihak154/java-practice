import java.util.Scanner;

public class IO {
    private static Scanner io = new Scanner(System.in);

    public static void displayMenu() {
        String menu = " 1. Add Locker\n" +
                " 2. Remove Locker\n" +
                " 3. Store Item\n" +
                " 4. Retrieve Item\n" +
                " 5. Display All Lockers\n" +
                " 6. Exit.\n" +
                "======================================\n\n";
        System.out.print(menu);
    }

    public static String getMenuChoice() {
        while (true) {
            String inputMenuChoice = io.nextLine();
            if (inputMenuChoice.equals("1") || inputMenuChoice.equals("2")
                    || inputMenuChoice.equals("3") || inputMenuChoice.equals("4")
                    || inputMenuChoice.equals("5") || inputMenuChoice.equals("6")) {
                return inputMenuChoice;
            } else {
                System.out.println("Please choose a valid menu option.");
            }
        }
    }

    public static String getInputLockerID() {
        while (true) {
            System.out.print("Enter locker ID: ");
            String inputLocker = io.nextLine().toUpperCase();
            if (inputLocker.length() != 4) {
                System.out.println("Error: locker ID must be 4 digits, starting with a letter.");
            } else {
                return inputLocker;
            }
        }
    }

    public static String getInputItem() {
        System.out.print("Enter item to add: ");
        String inputItem = io.nextLine();
        return inputItem;
    }

}
