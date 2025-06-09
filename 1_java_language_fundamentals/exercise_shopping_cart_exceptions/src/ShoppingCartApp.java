import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        // Tax exempt
        String taxExempt = promptUserForString("Are you tax-exempt? (yes/no)");

        // Address
        String[] addresses = {"123 Main St", "456 Main St", "789 Main St"};
        displayChoices(addresses);
        int addressIndex = promptUserForIntInRange("Shipping address?", 1, addresses.length);
        String selectedAddress = addresses[addressIndex - 1];

        // Shipping
        String shipping = promptUserForString("Shipping? (standard/2day/overnight)");

        // Order quantity
        int orderQuantity = promptUserForPositiveInt("Order quantity?");

        // Size
        String[] sizes = {"small", "medium", "large"};
        displayChoices(sizes);
        int sizeIndex = promptUserForIntInRange("Size?", 1, sizes.length);
        String selectedSize = sizes[sizeIndex - 1];

        // Promo code
        String promoCode = promptUserForString("Promo code for free shipping?");
        String promoDisplay;
        if (promoCode.isEmpty()) {
            promoDisplay = "None";
        } else {
            promoDisplay = promoCode;
        }

        // Print details
        System.out.println("\nOrder Details:");
        System.out.println("--------------");
        System.out.println("Tax Exempt: " + taxExempt);
        System.out.println("Shipping Address: " + selectedAddress);
        System.out.println("Shipping Method: " + shipping);
        System.out.println("Order Quantity: " + orderQuantity);
        System.out.println("Size: " + selectedSize);
        System.out.println("Promo Code: " + promoDisplay);
    }

    private static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }
    }

    private static String promptUserForString(String prompt) {
        Scanner io = new Scanner(System.in);
        System.out.println(prompt);
        return io.nextLine();
    }

    private static int promptUserForInt(String prompt) {
        while (true) {
            try {
                Scanner io = new Scanner(System.in);
                System.out.println(prompt);
                return Integer.parseInt(io.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static int promptUserForIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = promptUserForInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a valid menu option.");
        }
    }

    private static int promptUserForPositiveInt(String prompt) {
        while (true) {
            int value = promptUserForInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive number.");
        }
    }
}
