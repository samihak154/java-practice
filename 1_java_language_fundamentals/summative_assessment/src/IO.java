import java.util.Scanner;

public class IO {
    private static Scanner io = new Scanner(System.in);

    public static void displayMenu() {
        String menu = "What would you like to do next?\n" +
                " 1. Rent a Locker\n" +
                " 2. Access a Locker\n" +
                " 3. Release a Locker\n" +
                " Any other key to exit.\n" +
                "==================================\n" +
                "Enter your choice: ";
        System.out.print(menu);
    }

    public static String getMenuChoice() {
        String inputMenuChoice = io.nextLine();
        return inputMenuChoice;
    }

    public static int getInputLocker() {
        System.out.print("Enter locker number: ");
        int inputLocker = Integer.parseInt(io.nextLine());
        return inputLocker;
    }

    public static String getInputPin() {
        System.out.print("Enter pin number: ");
        String inputPin = io.nextLine();
        return inputPin;
    }

    public static void displayResult(Result r) {
        System.out.println(r.getMessage());
    }
}

