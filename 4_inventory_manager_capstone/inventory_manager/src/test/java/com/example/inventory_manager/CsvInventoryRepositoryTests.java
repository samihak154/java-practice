package com.example.inventory_manager;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.repository.CsvInventoryRepository;
import com.example.inventory_manager.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CsvInventoryRepositoryTests.TestConfig.class)
@TestPropertySource(properties = {
        "inventory.csv.filepath=data/test_inventory.csv",
        "inventory.csv.seedpath=data/seed_inventory.csv"
})

public class CsvInventoryRepositoryTests {
    @Autowired
    private InventoryRepository repository;

    @Value("${inventory.csv.seedpath}")
    private String seedPath;

    @Value("${inventory.csv.filepath}")
    private String testPath;

    @BeforeEach
    void setUp() throws IOException {
        Path seedFile = Paths.get(seedPath);
        Path targetFile = Paths.get(testPath);

        Files.copy(seedFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public InventoryRepository repository() {
            return new CsvInventoryRepository();
        }
    }


    @Test
    void getAllDataFromCSVFile() {
        List<Product> testInventory = repository.getAll();


        assertEquals(5, testInventory.size());
        assertFalse(testInventory.isEmpty());
    }


    @Test
    void addProductAndSaveToFile() {
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
    }

    @Test
    void cantDeleteProductNotFound() {
        String nonExistentId = "00020";
        List<Product> testInventory = repository.getAll();

        repository.delete(nonExistentId);

        assertEquals(5, testInventory.size());
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
