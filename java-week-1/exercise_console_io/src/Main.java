import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);

        // 1. Ask the user for their name
        System.out.print("What is your name? \n");
        String name = io.nextLine();
        System.out.println("\nHello, " + name + "! Let's get started with your order.\n");

        // 2. Get product details: Product name, quantity, unit price
        System.out.print("What product would you like to purchase? \n");
        String productName = io.nextLine();

        System.out.print("How many would you like?\n");
        String quantity = io.nextLine();

        System.out.print("What is the unit price?\n");
        String unitPrice = io.nextLine();

        // 3. Calculate total price
//            ○ Subtotal = Quantity * Unit Price
//            ○ Tax (7%) = Subtotal * 0.07
//            ○ Grand Total = Subtotal + Tax

        // 4. Print a formatted receipt
    }
}
