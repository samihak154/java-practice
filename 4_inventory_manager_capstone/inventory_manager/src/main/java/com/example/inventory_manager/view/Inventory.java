package com.example.inventory_manager.view;

import com.example.inventory_manager.model.PerishableProduct;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.RegularProduct;
import com.example.inventory_manager.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Inventory {
    private final InventoryService inventoryService;
    private final InventoryIO inventoryIO;

    @Autowired
    public Inventory(InventoryService inventoryService, InventoryIO inventoryIO) {
        this.inventoryService = inventoryService;
        this.inventoryIO = inventoryIO;
    }

    public void run() {
        inventoryIO.displayWelcome();

        boolean running = true;
        while (running) {
            int choice = inventoryIO.displayMenuAndGetChoice();

            switch (choice) {
                case 1:
                    handleAddingProduct();
                    break;
                case 2:
                    handleUpdatingProduct();
                    break;
                case 3:
                    handleDeletingProduct();
                    break;
                case 4:
                    handleSearchingForProduct();
                    break;
                case 5:
                    handleViewingProducts();
                    break;
                case 6:
                    running = false;
                    inventoryIO.displayGoodbye();
                    break;
                default:
                    inventoryIO.displayError("Invalid choice. Please try again.");
            }
        }
    }

    public String getProductIDWithSpecifications(String operation) {
        String productID;

        while (true) {
            if (operation == "add") {
                productID = inventoryIO.getStringInput("Enter 5-digit product ID (00001): ");
            } else if (operation == "update") {
                productID = inventoryIO.getStringInput("Enter product ID to update: ");
            } else if (operation == "delete") {
                productID = inventoryIO.getStringInput("Enter product ID to delete: ");
            } else {
                productID = inventoryIO.getStringInput("Enter product ID to search: ");
            }

            boolean numericalID = inventoryIO.containsOnlyDigits(productID);
            if (productID.length() == 5 && numericalID) {
                break;
            } else {
                inventoryIO.displayError("Product ID must be 5 digits and numbers only.");
            }
        }

        return productID;
    }

    public void handleAddingProduct() {
        String productID = getProductIDWithSpecifications("add");

        if (inventoryService.getProduct(productID) != null) {
            inventoryIO.displayError("Product with ID " + productID + " already exists! To update the product please select\n" +
                    "         the update option from the menu.\n");
            return;
        }

        String productName = inventoryIO.capitalize(inventoryIO.getStringInput("Enter product name: "));
        String category = inventoryIO.capitalize(inventoryIO.getStringInput("Enter category: "));
        int quantity = inventoryIO.getIntegerInput("Enter quantity to add: ");
        BigDecimal price = inventoryIO.getBigDecimalInput("Enter price per product: ");
        String productType = inventoryIO.getStringInput("Choose product type (Regular/Perishable): ").toUpperCase();
        String storageTemperature = inventoryIO.getStringInput(
                "Choose storage temp (Room-temp/Refrigerated/Frozen) or n/a: ").toUpperCase();
        String unitOfMeasure = inventoryIO.capitalize(inventoryIO.getStringInput("Enter unit per product or n/a: "));

        Product product;
        if (productType.equals("PERISHABLE")) {
            product = new PerishableProduct(productID, productName, category,
                    quantity, price, storageTemperature);
        } else {
            product = new RegularProduct(productID, productName, category,
                    quantity, price, unitOfMeasure);
        }

        inventoryService.addOrUpdateProduct(product);
        inventoryIO.displaySuccess("Product added to inventory!");
    }

    public void handleUpdatingProduct() {
        List<Product> products = inventoryService.getAllProducts();
        inventoryIO.displayInventoryItems(products);

        if (products.isEmpty()) {
            inventoryIO.displayError("Inventory is empty.");
            return;
        }

        String productID = getProductIDWithSpecifications("update");
        Product productToUpdate = inventoryService.getProduct(productID);

        if (productToUpdate == null) {
            inventoryIO.displayError("Product with ID " + productID + " was not found. To add a new product please\n" +
                    "         select the add option from the menu.\n");
            return;
        }

        int quantity = inventoryIO.getIntegerInput("Enter quantity to add: ");
        BigDecimal price = inventoryIO.getBigDecimalInput("Enter price per product: ");

        productToUpdate.setQuantity(quantity);
        productToUpdate.setPrice(price);

        inventoryService.addOrUpdateProduct(productToUpdate);
        inventoryIO.displaySuccess("Product updated!");
    }

    public void handleDeletingProduct() {
        List<Product> products = inventoryService.getAllProducts();
        inventoryIO.displayInventoryItems(products);

        if (products.isEmpty()) {
            inventoryIO.displayError("Inventory is empty.");
            return;
        }

        String productID = getProductIDWithSpecifications("delete");
        Product productToUpdate = inventoryService.getProduct(productID);

        if (productToUpdate == null) {
            inventoryIO.displayError("Product with ID " + productID + " was not found.");
            return;
        }

        boolean confirmed = inventoryIO.getConfirmation("Are you sure you want to delete product (y/n)? ");
        if (!confirmed) {
            inventoryIO.displayInfo("Remove cancelled.");
            return;
        }

        inventoryService.removeProduct(productID);
        inventoryIO.displaySuccess("Product removed from inventory!");
    }

    public void handleSearchingForProduct() {
        String productID = getProductIDWithSpecifications("search");
        Product product = inventoryService.getProduct(productID);

        if (product == null) {
            inventoryIO.displayError("Product with ID " + productID + " was not found.");
            return;
        }

        inventoryService.getProduct(productID);
        inventoryIO.displaySingleItem(product);
    }

    public void handleViewingProducts() {
        List<Product> products = inventoryService.getAllProducts();
        inventoryIO.displayInventoryItems(products);
    }
}