package org.example.command;

import org.example.io.ConsoleIO;
import org.example.service.ShoppingCartService;

public class DisplayCartCommand implements Command {
    private final ShoppingCartService shoppingCart;

    public DisplayCartCommand(ShoppingCartService shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void execute () {
        ConsoleIO.displayCartList(shoppingCart, false);
    }
}
