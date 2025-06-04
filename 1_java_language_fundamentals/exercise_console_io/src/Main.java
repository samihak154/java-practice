import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);

        // 1. Ask the user for their name
        System.out.print("What is your name? \n");
        String name = io.nextLine();
        System.out.println("\nHello, " + name + "! Let's get started with your order.");

        // 2. Get product details: Product name, quantity, unit price
        System.out.print("\nWhat product would you like to purchase? \n");
        String productName = io.nextLine();

        System.out.print("\nHow many would you like?\n");
        String inputQuantity = io.nextLine();
        double quantity = Double.parseDouble(inputQuantity);

        System.out.print("\nWhat is the unit price?\n");
        String inputUnitPrice = io.nextLine();
        double unitPrice = Double.parseDouble(inputUnitPrice);

        // 3. Calculate total price
        double productSubtotal = quantity * unitPrice;
        double productTax = productSubtotal * 0.07;
        double productGrandTotal = productSubtotal + productTax;

        // 4. Print a formatted receipt
        System.out.println("\nOrder Summary");
        System.out.println("---------------------------------");
        System.out.println("Product: \t\t" + productName);
        System.out.printf("Quantity: \t\t%.0f%n", quantity);
        System.out.printf("Price: \t\t\t$%.2f%n", unitPrice);
        System.out.println("---------------------------------");
        System.out.printf("Subtotal: \t\t$%.2f%n", productSubtotal);
        System.out.printf("Tax: \t\t\t$%.2f%n", productTax);
        System.out.printf("Grand Total: \t$%.2f%n", productGrandTotal);
        System.out.println("---------------------------------");
        System.out.println("Thank you for your order, " + name + "!");
    }
}
