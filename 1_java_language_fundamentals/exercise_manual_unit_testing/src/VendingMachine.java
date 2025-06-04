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
        String userChoice = io.nextLine().toLowerCase();

        System.out.println("\n");
        System.out.println(selectDrink(userChoice));
        System.out.println("\n");

        runTests();

    }

    public static String selectDrink(String choice) {
        switch (choice.toLowerCase()) {
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
        // Test "water"
        String test1 = selectDrink("water");
        System.out.print("Testing with water... ");
        if (test1.equals("You selected Water")) {
            System.out.println("Passed!");
        } else {
            System.out.println("Failed!");
        }

        // Test "soda"
        String test2 = selectDrink("soda");
        System.out.print("Testing with soda... ");
        if (test2.equals("You selected Soda")) {
            System.out.println("Passed!");
        } else {
            System.out.println("Failed!");
        }

        // Test "juice"
        String test3 = selectDrink("juice");
        System.out.print("Testing with juice... ");
        if (test3.equals("You selected Juice")) {
            System.out.println("Passed!");
        } else {
            System.out.println("Failed!");
        }
    }
}
