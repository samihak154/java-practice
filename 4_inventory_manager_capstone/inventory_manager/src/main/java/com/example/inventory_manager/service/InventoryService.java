package com.example.inventory_manager.service;

import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addOrUpdateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Product existingProduct = inventoryRepository.getByID(product.getProductID());

        if (existingProduct != null) {
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            inventoryRepository.update(existingProduct);
        } else {
            inventoryRepository.add(product);
        }
    }

    public void removeProduct(String productID) {
        inventoryRepository.delete(productID);
    }

    public Product getProduct(String productID) {
        return inventoryRepository.getByID(productID);
    }

    public List<Product> getAllProducts() {
        return inventoryRepository.getAll();
    }
}
