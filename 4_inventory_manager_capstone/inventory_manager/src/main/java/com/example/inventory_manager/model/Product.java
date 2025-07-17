package com.example.inventory_manager.model;

import java.math.BigDecimal;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected String category;
    protected int quantity;
    protected BigDecimal price;

    public Product (String productID, String productName, String category, int quantity, BigDecimal price) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public abstract ProductType getProductType();

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
