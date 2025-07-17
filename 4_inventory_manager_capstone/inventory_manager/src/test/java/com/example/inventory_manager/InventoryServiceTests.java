package com.example.inventory_manager;

import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.RegularProduct;
import com.example.inventory_manager.repository.SampleInventoryRepository;
import com.example.inventory_manager.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTests {
    private InventoryService inventoryService;
    private SampleInventoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SampleInventoryRepository();
        inventoryService = new InventoryService(repository);
    }

    @Test
    void addProductToInventory() {
        Product product = new RegularProduct("9999", "Test Product", "Test Category",
                10, new BigDecimal("29.99"), "12 count");

        inventoryService.addOrUpdateProduct(product);

        List<Product> testInventory = repository.getAll();
        Product foundProduct = repository.getByID("9999");

        assertTrue(testInventory.contains(foundProduct));
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(10, foundProduct.getQuantity());
    }

    @Test
    void addingNullProductThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.addOrUpdateProduct(null)
        );
        assertEquals("Item cannot be null", exception.getMessage());
    }



    @Test
    void updateProductIfItAlreadyExists() {
        Product product1 = new RegularProduct("9999", "Test Product", "Test Category",
                10, new BigDecimal("29.99"), "12 count");

        inventoryService.addOrUpdateProduct(product1);

        Product product2 = new RegularProduct("9999", "Test Product", "Test Category",
                15, new BigDecimal("25.99"), "12 count");

        inventoryService.addOrUpdateProduct(product2);

        Product foundProduct = repository.getByID("9999");
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(15, foundProduct.getQuantity());
        assertEquals(new BigDecimal("25.99"), foundProduct.getPrice());
    }
}
