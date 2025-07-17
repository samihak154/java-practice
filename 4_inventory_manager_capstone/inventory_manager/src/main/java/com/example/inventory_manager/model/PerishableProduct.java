package com.example.inventory_manager.model;

import java.math.BigDecimal;

public class PerishableProduct extends Product {
    private String storageTemperature;

    public PerishableProduct (String productID, String productName, String category, int quantity, BigDecimal price, String storageTemperature) {
        super(productID, productName, category, quantity, price);
        this.storageTemperature = storageTemperature;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.PERISHABLE;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(String storageTemperature) {
        this.storageTemperature = storageTemperature;
    }
}
