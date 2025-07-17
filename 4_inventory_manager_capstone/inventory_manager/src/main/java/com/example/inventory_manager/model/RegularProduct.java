package com.example.inventory_manager.model;

import java.math.BigDecimal;

public class RegularProduct extends Product {
    private String unitOfMeasure;

    public RegularProduct (String productID, String productName, String category, int quantity, BigDecimal price, String unitOfMeasure) {
        super(productID, productName, category, quantity, price);
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.REGULAR;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
