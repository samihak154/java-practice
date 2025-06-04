import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setup product
        Product product = new Product(
                1, 2, 2.56, 4.99,
                "Squeezable Treat Variety Pack"
        );

        // Get user input
        System.out.print("Are you tax-exempt? (yes/no): ");
        boolean isTaxExempt = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Shipping? (standard/2day/overnight): ");
        String shippingType = scanner.nextLine();

        System.out.print("Order Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Promo code for free shipping: ");
        String promoCode = scanner.nextLine();

        // Create cart
        ShoppingCart cart = new ShoppingCart(product, quantity, isTaxExempt, shippingType, promoCode);

        // Print formatted receipt
        ReceiptPrinter.printReceipt(cart, product);
    }
}