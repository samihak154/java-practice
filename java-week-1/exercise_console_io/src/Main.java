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
        String inputQuantity = io.nextLine();
        double quantity = Double.parseDouble(inputQuantity);

        System.out.print("What is the unit price?\n");
        String inputUnitPrice = io.nextLine();
        double unitPrice = Double.parseDouble(inputUnitPrice);

        // 3. Calculate total price
        double productSubtotal = quantity * unitPrice;
        double productTax = productSubtotal * 0.07;
        double productGrandTotal = productSubtotal + productTax;

        // 4. Print a formatted receipt
    }
}
