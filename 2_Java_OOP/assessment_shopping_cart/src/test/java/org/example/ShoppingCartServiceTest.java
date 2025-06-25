package org.example;

import org.example.model.Product;
import org.example.service.ShoppingCartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartServiceTest {
    ShoppingCartService cart = new ShoppingCartService();

    @Test
    @DisplayName("Empty HashMap initialized")
    public void hashMapInitializeAsEmpty() {
        Map<Product, Integer> testCart = cart.getCart();

        assertTrue(testCart.isEmpty());
    }

    @Test
    @DisplayName("Returns copy of cart with correct items")
    public void getCartReturnsCart() {
        Product milk = new Product("1001","Milk", 299);
        cart.addItem(milk, 2);
        Map<Product, Integer> testCart = cart.getCart();

        assertEquals(1, testCart.size());
        assertTrue(testCart.containsKey(milk));
    }

    @Test
    @DisplayName("Show quantity of item in cart")
    public void getCorrectQuantityOfItemFromCart() {
        Product milk = new Product("1001","Milk", 299);
        Product eggs = new Product("1002","Eggs", 599);
        cart.addItem(eggs, 2);
        cart.getQuantity(milk);

        assertEquals(0, cart.getQuantity(milk));
        assertEquals(2, cart.getQuantity(eggs));
    }

    @Test
    @DisplayName("Items are added to cart")
    public void addItemsToCart() {
        Product milk = new Product("1001","Milk", 299);
        Product eggs = new Product("1002","Egg", 499);
        cart.addItem(milk, 1);
        cart.addItem(milk, 1);
        cart.addItem(eggs, 1);

        assertEquals(2, cart.getCart().get(milk));
        assertEquals(1, cart.getCart().get(eggs));
    }

    @Test
    @DisplayName("Throw exception when trying to add negative quantity")
    public void throwErrorWhenTryingToAddNegativeQuantity() {
        Product milk = new Product("1001","Milk", 299);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem(milk, -1);
        });

        assertEquals("Error: Please enter a positive quantity.\n", exception.getMessage());
        assertFalse(cart.getCart().containsKey(milk));
    }

    @Test
    @DisplayName("Items are removed from cart")
    public void removeItemReducesQuantity() {
        Product milk = new Product("1001","Milk", 299);
        cart.addItem(milk, 2);
        cart.removeItem(milk, 1);

        assertEquals(1, cart.getQuantity(milk));
    }

    @Test
    @DisplayName("Return false if item isn't found in cart")
    public void removeItemFailsIfItemNotInCart() {
        Product milk = new Product("1001","Milk", 299);

        assertFalse(cart.removeItem(milk, 2));
    }

    @Test
    @DisplayName("Return false if amount to remove exceeds the item amount in cart")
    public void removeItemFailsIfAmountToRemoveExceedsItemAmountInCart() {
        Product milk = new Product("1001","Milk", 299);
        cart.addItem(milk, 2);
        cart.removeItem(milk, 3);

        assertFalse(cart.removeItem(milk, 3));
    }

    @Test
    @DisplayName("Shopping cart is completely emptied")
    public void clearAllItemsFromCart() {
        Product milk = new Product("1001","Milk", 299);
        cart.addItem(milk, 2);
        cart.clearCart();

        assertTrue(cart.getCart().isEmpty());
    }

    @Test
    @DisplayName("Calculates subtotal based on item quantities and set prices")
    public void getSubTotalOfAllItems() {
        Product milk = new Product("1001","Milk", 299);
        Product eggs = new Product("1002","Egg", 499);
        cart.addItem(milk, 1);
        cart.addItem(eggs, 2);

        assertEquals(12.97, cart.getSubTotal());
    }

    @Test
    @DisplayName("New cart should be empty")
    void newCartIsEmpty() {
        assertTrue(cart.isEmpty());
    }

    @Test
    @DisplayName("Cart with items should not be empty")
    void cartWithItemsIsNotEmpty() {
        Product milk = new Product("1001", "Milk", 299);

        cart.addItem(milk, 1);
        assertFalse(cart.isEmpty());
    }
}
