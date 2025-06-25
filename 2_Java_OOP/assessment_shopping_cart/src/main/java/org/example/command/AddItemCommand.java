package org.example.command;

import org.example.io.ConsoleIO;
import org.example.model.Product;
import org.example.service.ProductCatalogService;
import org.example.service.ShoppingCartService;

public class AddItemCommand implements Command {
    private final ShoppingCartService shoppingCart;
    private final ProductCatalogService catalogService;

    public AddItemCommand(ShoppingCartService shoppingCart, ProductCatalogService catalogService) {
        this.shoppingCart = shoppingCart;
        this.catalogService = catalogService;
    }

    @Override
    public void execute() {
        try {
            ConsoleIO.displayAvailableProducts(catalogService.getAllProducts());

            String productId = ConsoleIO.getString("Enter product ID: ");
            int quantity = ConsoleIO.getInt("Enter quantity of item: ");

            Product product = catalogService.getProductById(productId);
            shoppingCart.addItem(product, quantity);

            ConsoleIO.displayMessage("Added " + quantity + " " + product.getName() + "(s) to cart!\n");
        } catch (IllegalArgumentException e) {
            ConsoleIO.displayMessage(e.getMessage());
        }
    }
}
