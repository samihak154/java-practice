package org.example.model;

import java.util.*;

public class ProductCatalog {
    private final List<Product> catalog;

    public ProductCatalog() {
        catalog = new ArrayList<>();
        catalog.add(new Product("1001", "Milk", 299));
        catalog.add(new Product("1002", "Bread", 199));
        catalog.add(new Product("1003", "Eggs", 599));
        catalog.add(new Product("1004", "Rice", 499));
        catalog.add(new Product("1005", "Butter", 199));
        catalog.add(new Product("1006", "Chicken", 799));
        catalog.add(new Product("1007", "Apples", 99));
        catalog.add(new Product("1008", "Onions", 99));
        catalog.add(new Product("1009", "Water", 299));
        catalog.add(new Product("1010", "Coffee", 499));
    }

    public List<Product> getCopyOfAllProducts() {
        return Collections.unmodifiableList(catalog);
    }
}
