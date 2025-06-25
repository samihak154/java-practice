package org.example.service;

import org.example.model.Product;
import org.example.model.ProductCatalog;

import java.util.List;

public class ProductCatalogService {
    private final ProductCatalog catalog;

    public ProductCatalogService(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public List<Product> getAllProducts(){
        return catalog.getCopyOfAllProducts();
    }

    public Product getProductById(String id) {
        for (Product product : catalog.getCopyOfAllProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product not found: " + id);
    }

    public Product getProductByName(String name) {
        String lowerName = name.toLowerCase();
        for (Product product : catalog.getCopyOfAllProducts()) {
            if (product.getName().toLowerCase().equals(lowerName)) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product not found: " + name);
    }

    public int getPriceById(String id) {
        return getProductById(id).getPriceInCents();
    }
}
