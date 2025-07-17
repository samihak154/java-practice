package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.PerishableProduct;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.RegularProduct;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvInventoryRepository implements InventoryRepository {

    private final Map<String, Product> inventory = new HashMap<>();
    @Value("${inventory.csv.filepath:data/inventory.csv}")
    private String filename;

    @PostConstruct
    public void init() {
        loadFromFile();
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
        saveToFile();
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
        saveToFile();
    }

    @Override
    public void delete(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        inventory.remove(productID);
        saveToFile();
    }

    @Override
    public Product getByID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        return inventory.get(productID);
    }

    private void loadFromFile() {
        File file = new File(filename);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length == 8) {
                    String productID = parts[0].trim();
                    String productName = parts[1].trim();
                    String category = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());
                    BigDecimal price = new BigDecimal(parts[4].trim());
                    String productType = parts[5].trim();
                    String storageTemperature = parts[6].trim();
                    String unitOfMeasure = parts[7].trim();

                    Product product;
                    if (productType.equals("PERISHABLE")) {
                        product = new PerishableProduct(productID, productName, category,
                                quantity, price, storageTemperature);
                    } else {
                        product = new RegularProduct(productID, productName, category,
                                quantity, price, unitOfMeasure);
                    }

                    inventory.put(productID, product);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ProductID,ProductName,Category,Quantity,Price,ProductType,StorageTemp,UnitOfMeasure");

            for (Product product : inventory.values()) {
                String csvLine;
                if (product instanceof PerishableProduct) {
                    PerishableProduct perishable = (PerishableProduct) product;
                    csvLine = String.format("%s,%s,%s,%d,%.2f,%s,%s,",
                            product.getProductID(),
                            product.getProductName(),
                            product.getCategory(),
                            product.getQuantity(),
                            product.getPrice(),
                            product.getProductType(),
                            perishable.getStorageTemperature()
                    );
                }
                else {
                    RegularProduct regular = (RegularProduct) product;
                    csvLine = String.format("%s,%s,%s,%d,%.2f,%s,,%s",
                            product.getProductID(),
                            product.getProductName(),
                            product.getCategory(),
                            product.getQuantity(),
                            product.getPrice(),
                            product.getProductType(),
                            regular.getUnitOfMeasure()
                    );
                }
                writer.println(csvLine);
            }
        } catch (IOException e) {
            System.err.println("Error saving to inventory: " + e.getMessage());
        }
    }
}
