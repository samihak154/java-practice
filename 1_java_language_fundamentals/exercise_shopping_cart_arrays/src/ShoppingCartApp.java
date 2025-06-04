import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        // Declare and assign arrays
        String[] addresses = {"123 Main St", "456 Main St", "789 Main St"};
        String[] productSizes = {"small", "medium", "large"};
        Scanner io = new Scanner(System.in);

        // Loop through the array to show the addresses to user
        // and store user selected address in a variable
        for (int i = 0; i < addresses.length; i++) {
            System.out.println((i+1) + ". " + addresses[i]);
        }
        System.out.println("Shipping address? (1, 2, or 3) ");
        int addressChoice = Integer.parseInt(io.nextLine());
        String selectedAddress = addresses[addressChoice - 1];

        // Get order quantity from user
        System.out.println("Order quantity? ");
        String quantity = io.nextLine();

        // Loop through the array to show the product sizes to user
        // and store user selected size in a variable
        for (int i = 0; i < productSizes.length; i++) {
            System.out.println((i+1) + ". " + productSizes[i]);
        }
        System.out.println("Product size? (1, 2, or 3) ");
        int sizeChoice = Integer.parseInt(io.nextLine());
        String selectedSize = productSizes[sizeChoice - 1];

        // Get promo code from user
        System.out.println("Promo code for shipping? ");
        String shippingCode = io.nextLine();
        
        //Print details
        System.out.println("\n-----------------------------");
        System.out.println("Shipping Details");
        System.out.println("-----------------------------");
        System.out.println("Shipping address: " + selectedAddress);
        System.out.println("Order quantity: " + quantity);
        System.out.println("Product size: " + selectedSize);
        System.out.println("Promo code: " + shippingCode);
        System.out.println("-----------------------------");
    }
}
