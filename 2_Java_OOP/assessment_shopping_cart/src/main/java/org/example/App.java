package org.example;

import org.example.io.ConsoleIO;
import org.example.model.ProductCatalog;
import org.example.service.ProductCatalogService;
import org.example.service.ShoppingCartService;
import org.example.command.AddItemCommand;
import org.example.command.RemoveItemCommand;
import org.example.command.DisplayCartCommand;
import org.example.command.CheckoutCommand;

public class App {
    public static void main(String[] args) {
        ShoppingCartService shoppingCart = new ShoppingCartService();
        ProductCatalog productCatalog = new ProductCatalog();
        ProductCatalogService catalogService = new ProductCatalogService(productCatalog);

        ConsoleIO.displayMenu();
        boolean running = true;

        do {
            String choice = ConsoleIO.getMenuChoice();
            switch (choice) {
                case "1":
                    new AddItemCommand(shoppingCart,catalogService).execute();
                    break;
                case "2":
                    new RemoveItemCommand(shoppingCart, catalogService).execute();
                    break;
                case "3":
                    new DisplayCartCommand(shoppingCart).execute();
                    break;
                case "4":
                    new CheckoutCommand(shoppingCart).execute();
                    break;
                case "5":
                    System.out.println("Goodbye!\n");
                    running = false;
                    break;
            }
        } while (running);
    }
}
