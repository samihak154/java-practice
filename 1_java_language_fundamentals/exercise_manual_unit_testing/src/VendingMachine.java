import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        System.out.println("Welcome to the Vending Machine!");
        System.out.println("Please select your drink:" +
                           "\nWater" +
                           "\nSoda" +
                           "\nJuice" +
                           "\n----------------------");

        Scanner io = new Scanner(System.in);
        String userChoice = io.nextLine();

        System.out.println("\n");
        System.out.println(selectDrink(userChoice, true));
        System.out.println("\n");

        runTests();

    }

    public static String selectDrink(String choice, boolean showDebug) {
        switch (choice) {
            case "water":
                return "You selected Water";
            case "soda":
                return "You selected Soda";
            case "juice":
                return "You selected Juice";
            default:
                return "Invalid selection";
        }
    }

    public static void runTests() {
        String[] inputs = {"water", "soda", "juice", "WATER", "Tea", ""};
        String[] expected = {
                "You selected Water",
                "You selected Soda",
                "You selected Juice",
                "You selected Water",
                "Invalid selection",
                "Invalid selection"
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.print("Testing with " + inputs[i] + "... ");
            String actual = selectDrink(inputs[i], false); // false = no debug
            if (expected[i].equals(actual)) {
                System.out.println("Passed!");
            } else {
                System.out.println("FAIL: Expected '" + expected[i] + "', got '" + actual + "'");
            }
        }
    }
}
