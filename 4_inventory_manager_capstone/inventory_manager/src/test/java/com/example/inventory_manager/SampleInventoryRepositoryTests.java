package com.example.inventory_manager;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.repository.SampleInventoryRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SampleInventoryRepositoryTests {
    SampleInventoryRepository repository = new SampleInventoryRepository();

    @Test
    void getAllDataFromSampleInventory() {
        List<Product> testInventory = repository.getAll();


        assertEquals(10, testInventory.size());
        assertFalse(testInventory.isEmpty());
    }

    @Test
    void addProductToHashMap() {
        Product product = new RegularProduct("99999", "Test Product", "Test Category",
                10, new BigDecimal("29.99"), "12 count");

        repository.add(product);

        Product foundProduct = repository.getByID("99999");
        assertEquals("Test Product", foundProduct.getProductName());
    }

    @Test
    void updateProductAndSaveToFile() {
        Product product2 = new RegularProduct("99998", "Test Product2", "Test Category",
                10, new BigDecimal("29.99"), "12 count");
        repository.add(product2);

        Product updatedProduct = new RegularProduct("99998", "Updated Test Product2", "Test Category",
                20, new BigDecimal("17.99"), "8 count");
        repository.update(updatedProduct);

        Product foundProduct = repository.getByID("99998");
        assertEquals("Updated Test Product2", foundProduct.getProductName());
        assertEquals(new BigDecimal("17.99"), foundProduct.getPrice());
    }

    @Test
    void deleteProductAndSaveToFile() {
        Product product = repository.getByID("00003");
        String productID = product.getProductID();

        repository.delete(productID);

        List<Product> testInventory = repository.getAll();
        assertFalse(testInventory.contains(product));
        assertEquals(9, testInventory.size());
    }

    @Test
    void cantDeleteProductNotFound() {
        String nonExistentId = "00020";
        List<Product> testInventory = repository.getAll();

        repository.delete(nonExistentId);

        assertEquals(10, testInventory.size());
    }

    @Test
    void getProductGivenID() {
        Product product = repository.getByID("00001");
        String productName = product.getProductName();

        assertEquals("Wheat bread", productName);
    }

    @Test
    void assertFalseForProductNotFound() {
        Product product = repository.getByID("00020");

        List<Product> testInventory = repository.getAll();
        assertFalse(testInventory.contains(product));
    }
}
