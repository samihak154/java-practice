package org.example.service;

import org.example.model.Product;

import java.util.Map;
import java.util.HashMap;

public class ShoppingCartService {
    Map<Product, Integer> shoppingCart;

    public ShoppingCartService() {
        shoppingCart = new HashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return new HashMap<>(shoppingCart);
    }

    public int getQuantity(Product product) {
        if (shoppingCart.containsKey(product)) {
            int quantity = shoppingCart.get(product);
            return quantity;
        }
        return 0;
    }

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Error: Please enter a positive quantity.\n");
        }
        int currentQty = getQuantity(product);
        if (currentQty > 0) {
            shoppingCart.put(product, currentQty + quantity);
        } else {
            shoppingCart.put(product, quantity);
        }
    }

    public boolean removeItem(Product product, int quantityToRemove) {
        if (quantityToRemove <= 0) {
            throw new IllegalArgumentException("Error: Please enter a positive quantity.\n");
        }
        if (!shoppingCart.containsKey(product)) {
            return false;
        }
        int currentQuantity = shoppingCart.get(product);
        if (currentQuantity < quantityToRemove) {
            return false;
        }
        if (currentQuantity == quantityToRemove) {
            shoppingCart.remove(product);
        } else {
            shoppingCart.replace(product, currentQuantity - quantityToRemove);
        }
        return true;
    }

    public void clearCart() {
        shoppingCart.clear();
    }

    public double getSubTotal() {
        int subtotalInCents = 0;
        for (Product product : shoppingCart.keySet()) {
            subtotalInCents += product.getPriceInCents() * shoppingCart.get(product);
        }
        return subtotalInCents / 100.0;
    }

    public boolean isEmpty() {
        return shoppingCart.isEmpty();
    }
}
