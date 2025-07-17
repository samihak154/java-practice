package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.Product;

import java.util.List;

public interface InventoryRepository {
    List<Product> getAll();

    void add(Product item);

    void update(Product item);

    void delete(String productID);

    Product getByID(String productID);
}
