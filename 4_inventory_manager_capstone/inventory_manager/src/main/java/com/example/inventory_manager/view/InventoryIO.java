package com.example.inventory_manager.view;

import com.example.inventory_manager.model.PerishableProduct;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.ProductType;
import com.example.inventory_manager.model.RegularProduct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class InventoryIO {
    private final Scanner scanner;

    public InventoryIO() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println();
        System.out.println("═════════════════════════════════════════");
        System.out.println("    Welcome to Inventory Management!");
        System.out.println("═════════════════════════════════════════");
        System.out.println();
    }

    public void displayGoodbye() {
        System.out.println();
        System.out.println("Thank you for using Inventory Management!");
        System.out.println();
    }

    public int displayMenuAndGetChoice() {
        System.out.println();
        System.out.println("======= MENU =======");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Search For Product");
        System.out.println("5. View All Products");
        System.out.println("6. Quit");
        System.out.println();

        return getIntegerInputWithDefault("Please select an option (1-6): ", -1);
    }

    public void displaySuccess(String message) {
        System.out.println("✓ SUCCESS: " + message);
    }

    public void displayError(String message) {
        System.out.println("✗ ERROR: " + message);
    }

    public void displayInfo(String message) {
        System.out.println("ℹ " + message);
    }

    public void displayInventoryItems(List<Product> products) {
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                                                    INVENTORY");
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        if (products.isEmpty()) {
            System.out.println("                                              No items in inventory");
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
            return;
        }

        List<Product> sortedProducts = products.stream()
                .sorted(Comparator.comparing(Product::getProductID))
                .collect(Collectors.toList());

        System.out.printf("%-12s %-20s %-20s %4s %9s %12s %15s %15s%n", "ID", "PRODUCT", "CATEGORY", "QTY", "PRICE", "TYPE", "TEMP", "UNITS");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

        for (Product product : sortedProducts) {
            String id = product.getProductID();
            String productName = product.getProductName();
            String category = product.getCategory();
            int quantity = product.getQuantity();
            BigDecimal price = product.getPrice();
            ProductType productType = product.getProductType();
            String storageTemp = "N/A";
            String unitOfMeasure = "N/A";

            if (product instanceof PerishableProduct) {
                PerishableProduct perishable = (PerishableProduct) product;
                storageTemp = perishable.getStorageTemperature();
            } else if (product instanceof RegularProduct) {
                RegularProduct regular = (RegularProduct) product;
                unitOfMeasure = regular.getUnitOfMeasure();
            }

            if (productName.length() > 20) {
                productName = productName.substring(0, 17) + "...";
            }

            if (category.length() > 17) {
                category = category.substring(0, 14) + "...";
            }

            System.out.printf("%-12s %-20s %-20s %3d      $%-12.2f %-15s %-14s %-15s%n",
                    id, productName, category, quantity, price, productType, storageTemp, unitOfMeasure);
        }

        System.out.println("══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println();
    }

    public void displaySingleItem(Product product) {
        System.out.println();
        System.out.println("═════════════════════════════");
        System.out.println("ITEM DETAILS");
        System.out.println("═════════════════════════════");
        System.out.printf("Product ID:  %s%n", product.getProductID());
        System.out.printf("Product:     %s%n", product.getProductName());
        System.out.printf("Category:    %s%n", product.getCategory());
        System.out.printf("Quantity:    %d%n", product.getQuantity());
        System.out.printf("Price:       $%.2f%n", product.getPrice());
        System.out.printf("Type:        %s%n", product.getProductType());

        if (product instanceof PerishableProduct) {
            PerishableProduct perishable = (PerishableProduct) product;
            System.out.printf("Temp:        %s%n", perishable.getStorageTemperature());
            System.out.printf("Units:       N/A%n");
        } else if (product instanceof RegularProduct) {
            RegularProduct regular = (RegularProduct) product;
            System.out.printf("Temp:        N/A%n");
            System.out.printf("Units:       %s%n", regular.getUnitOfMeasure());
        }

        System.out.println("═════════════════════════════");
        System.out.println();
    }

    public boolean containsOnlyDigits(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        return string.chars().allMatch(Character::isDigit);
    }

    public String capitalize(String string) {
        if (string == null || string.isEmpty()) return string;
        String capitalizedString = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return capitalizedString;
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            displayError("Input cannot be empty. Please try again.");
            return getStringInput(prompt);
        }

        return input;
    }

    public Integer getIntegerInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            displayError("Input cannot be empty. Please try again.");
            return getIntegerInput(prompt);
        }

        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                displayError("Please enter a positive number.");
                return getIntegerInput(prompt);
            }
            return value;
        } catch (NumberFormatException e) {
            displayError("Please enter a valid number.");
            return getIntegerInput(prompt);
        }
    }

    public BigDecimal getBigDecimalInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            displayError("Input cannot be empty. Please try again.");
            return getBigDecimalInput(prompt);
        }

        try {
            BigDecimal value = new BigDecimal(input);
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                displayError("Please enter a positive price.");
                return getBigDecimalInput(prompt);
            }
            return value;
        } catch (NumberFormatException e) {
            displayError("Please enter a valid price (e.g., 19.99).");
            return getBigDecimalInput(prompt);
        }
    }

    private int getIntegerInputWithDefault(String prompt, int defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            displayError("Please enter a valid number.");
            return getIntegerInputWithDefault(prompt, defaultValue);
        }
    }

    public boolean getConfirmation(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("y") && !input.equals("n") &&
                !input.equals("yes") && !input.equals("no")) {
            displayError("Please enter 'y' for yes or 'n' for no.");
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.equals("y") || input.equals("yes");
    }
}