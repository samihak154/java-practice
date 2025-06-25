package org.example.command;

import org.example.io.ConsoleIO;
import org.example.service.ShoppingCartService;

public class CheckoutCommand implements Command {
    private final ShoppingCartService shoppingCart;

    public CheckoutCommand(ShoppingCartService shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void execute() {
        if (shoppingCart.isEmpty()) {
            ConsoleIO.displayMessage("Error: Cart is empty, please add items to cart!\n");
            return;
        }

        ConsoleIO.displayCartList(shoppingCart, true);
        String confirmCheckout = ConsoleIO.getString("Confirm checkout (yes/no): ");

        if (confirmCheckout.equals("yes")) {
            shoppingCart.clearCart();
            ConsoleIO.displayMessage("Checkout successful!\n");
            return;
        }
        ConsoleIO.displayMessage("Checkout cancelled.\n");
    }
}
