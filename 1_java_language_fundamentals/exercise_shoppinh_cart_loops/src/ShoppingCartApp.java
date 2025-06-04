import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        System.out.println("Welcome to the shopping cart app!");

        // Variables declared outside the loop so we can use them later
        boolean isConfirmed = false;
        String taxExempt = "";
        String shipping = "";
        String promoCode = "";
        int orderQuantity = 0;

        // Keep asking for order details until user confirms
        while (!isConfirmed) {
            // Ask for all order details
            System.out.print("Are you tax-exempt? (yes/no): ");
            taxExempt = io.nextLine();

            System.out.print("Shipping? (standard/2day/overnight): ");
            shipping = io.nextLine();

            System.out.print("Order quantity?: ");
            orderQuantity = Integer.parseInt(io.nextLine());

            System.out.print("Promo code for free shipping?: ");
            promoCode = io.nextLine();

            // Ask for confirmation
            System.out.print("Confirm Order (yes/no): ");
            isConfirmed = "yes".equalsIgnoreCase(io.nextLine());
        }

        // Print all the details after confirmation
        System.out.println("\n----------------------");
        System.out.println("Order Details:");
        System.out.println("----------------------");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Promo code: " + promoCode);
    }
}
