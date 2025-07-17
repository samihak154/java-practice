package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.PerishableProduct;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.ProductType;
import com.example.inventory_manager.model.RegularProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleInventoryRepository implements InventoryRepository {
    private final Map<String, Product> inventory = new HashMap<>();

    public SampleInventoryRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Perishable products
        addSampleProduct("00001", "Wheat bread", "Dry grocery",
                50, new BigDecimal(2.79), ProductType.PERISHABLE, "Room temperature");
        addSampleProduct("00002", "Sourdough bread", "Dry grocery",
                30, new BigDecimal(3.99), ProductType.PERISHABLE, "Room temperature");
        addSampleProduct("00003", "Whole milk", "Dairy",
                44, new BigDecimal(2.89), ProductType.PERISHABLE, "Refrigerated");
        addSampleProduct("00004", "Oat milk", "Dairy",
                45, new BigDecimal(5.44), ProductType.PERISHABLE, "Refrigerated");
        addSampleProduct("00005", "Butter", "Dairy",
                60, new BigDecimal(2.56), ProductType.PERISHABLE, "Refrigerated");

        // Regular products
        addSampleProduct("00006", "Macbook air 3", "Electronics",
                7, new BigDecimal(899.86), ProductType.REGULAR, "1");
        addSampleProduct("00007", "iPhone 14 pro", "Electronics",
                10, new BigDecimal(755.52), ProductType.REGULAR, "1");
        addSampleProduct("00008", "Toilet paper", "Everyday essentials",
                100, new BigDecimal(22.82), ProductType.REGULAR, "24 rolls");
        addSampleProduct("00009", "Trash bags", "Everyday essentials",
                63, new BigDecimal(12.97), ProductType.REGULAR, "80 count");
        addSampleProduct("00010", "Laundry pods", "Everyday essentials",
                88, new BigDecimal(20.77), ProductType.REGULAR, "80 count");
    }

    private void addSampleProduct(String productID, String productName, String category, int quantity, BigDecimal price, ProductType productType, String specificField) {
        if (productType == ProductType.PERISHABLE) {
            Product perishableProduct = new PerishableProduct(productID, productName, category, quantity, price, specificField);
            inventory.put(productID, perishableProduct);
        } else {
            Product regularProduct = new RegularProduct(productID, productName, category, quantity, price, specificField);
            inventory.put(productID, regularProduct);
        }
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        inventory.put(product.getProductID(), product);
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        String productID = product.getProductID();
        if (!inventory.containsKey(productID)) {
            throw new IllegalArgumentException("Product with ID " + productID + " not found");
        }
        inventory.put(productID, product);
    }

    @Override
    public void delete(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        inventory.remove(productID);
    }

    @Override
    public Product getByID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        return inventory.get(productID);
    }
}
