package org.example.io;

import org.example.model.Product;
import org.example.service.ShoppingCartService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ConsoleIO {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        String menu = "========= Main Menu =========\n" +
                "1. Add Item\n" +
                "2. Remove Item\n" +
                "3. Display Cart\n" +
                "4. Checkout\n" +
                "5. Quit\n" +
                "=============================\n";
        System.out.print(menu);
    }

    public static String getMenuChoice() {
        String menuChoice = "";
        while (true) {
            System.out.print("Choose an option: ");
            menuChoice = scanner.nextLine();
            if (menuChoice.matches("[1-5]")) {
                break;
            }
            System.out.print("Please choose a valid menu option!\n");
        }
        return menuChoice;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        String stringInput = scanner.nextLine();
        return stringInput;
    }

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayAvailableProducts(List<Product> products) {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.printf("%s: %-15s $%.2f%n",
                    product.getId(),
                    product.getName(),
                    product.getPriceInCents() / 100.0);
        }
    }

    public static String formatCartItem(Product product, int quantity) {
        double price = (product.getPriceInCents() / 100.0);
        double totalPrice = price * quantity;
        String name = product.getName().toUpperCase();
        if (name.length() > 15) {
            name = name.substring(0, 12) + "...";
        }
        return String.format("%-18s %-9d $%-11.2f $%4.2f%n",
                name,
                quantity,
                price,
                totalPrice);
    }

    public static void displayCartList(ShoppingCartService shoppingCart, boolean checkout) {
        String title = checkout ? "CHEKOUT" : "CART";
        System.out.printf("%n%s:%n", title);
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-6s %-15s %-10s %-10s %9s%n", "ITEM", "NAME", "QUANTITY", "UNIT PRICE", "TOTAL");
        System.out.println("-------------------------------------------------------");

        int itemNumber = 1;
        for (Map.Entry<Product, Integer> entry : shoppingCart.getCart().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            System.out.printf("%-6s %s%n",
                    (itemNumber++) + ".",
                    formatCartItem(product, qty));
        }

        System.out.println("\n-------------------------------------------------------");
        System.out.printf("Subtotal: $%.2f%n", shoppingCart.getSubTotal());
        System.out.println("-------------------------------------------------------\n");
    }
}
