package org.example.command;

import org.example.io.ConsoleIO;
import org.example.model.Product;
import org.example.service.ProductCatalogService;
import org.example.service.ShoppingCartService;

public class RemoveItemCommand implements Command {
    private final ShoppingCartService shoppingCart;
    private final ProductCatalogService catalogService;

    public RemoveItemCommand(ShoppingCartService shoppingCart, ProductCatalogService productCatalog) {
        this.shoppingCart = shoppingCart;
        this.catalogService = productCatalog;
    }

    @Override
    public void execute() {
        if (shoppingCart.isEmpty()) {
            ConsoleIO.displayMessage("Error: No items to remove!\n");
            return;
        }

        ConsoleIO.displayCartList(shoppingCart, false);
        String productName = ConsoleIO.getString("Enter name of product to remove: ");

        try {
            Product productToRemove = catalogService.getProductByName(productName);
            if (!shoppingCart.getCart().containsKey(productToRemove)) {
                ConsoleIO.displayMessage("Error: " + productName + " is not in cart!\n");
                return;
            }

            int quantityToRemove = ConsoleIO.getInt("Enter quantity to remove: ");
            if (!shoppingCart.removeItem(productToRemove, quantityToRemove)) {
                ConsoleIO.displayMessage("Error: Quantity exceeds the amount in cart!\n");
            } else {
                ConsoleIO.displayMessage("Removed " + quantityToRemove + " " + productToRemove.getName() + "(s)\n");
            }
        } catch (IllegalArgumentException e) {
            ConsoleIO.displayMessage(e.getMessage());
        }
    }
}

