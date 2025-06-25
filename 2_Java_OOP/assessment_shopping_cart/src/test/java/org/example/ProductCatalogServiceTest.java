package org.example;

import org.example.model.Product;
import org.example.model.ProductCatalog;
import org.example.service.ProductCatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogServiceTest {
    private ProductCatalogService catalogService;
    private ProductCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new ProductCatalog();
        catalogService = new ProductCatalogService(catalog);
    }

    @Test
    @DisplayName("Return all default products")
    void getAllProductsReturnsAllDefaultProducts() {
        List<Product> products = catalogService.getAllProducts();

        assertEquals(10, products.size());
    }

    @Test
    @DisplayName("Return product when searching by ID")
    void returnProductWhenSearchingById() {
        Product milk = catalogService.getProductById("1001");

        assertEquals("1001", milk.getId());
        assertEquals("Milk", milk.getName());
        assertEquals(299, milk.getPriceInCents());
    }

    @Test
    @DisplayName("Throw exception for searching invalid ID")
    void throwExceptionWhenSearchingInvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            catalogService.getProductById("1234");
        });

        assertEquals("Product not found: 1234", exception.getMessage());
    }

    @Test
    @DisplayName("Return product when searching by name")
    void returnProductWhenSearchingByName() {
        Product milk = catalogService.getProductByName("Milk");

        assertEquals("1001", milk.getId());
        assertEquals("Milk", milk.getName());
        assertEquals(299, milk.getPriceInCents());
    }

    @Test
    @DisplayName("Throw exception for searching invalid name")
    void throwExceptionWhenSearchingInvalidName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            catalogService.getProductByName("Oat Milk");
        });

        assertEquals("Product not found: Oat Milk", exception.getMessage());
    }

    @Test
    @DisplayName("Return price when searching by ID")
    void returnPriceWhenSearchingByID() {
        int price1 = catalogService.getPriceById("1001");
        int price2 = catalogService.getPriceById("1003");

        assertEquals(299, price1);
        assertEquals(599, price2);
    }
}
