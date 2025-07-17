package com.example.inventory_manager.config;

import com.example.inventory_manager.repository.CsvInventoryRepository;
import com.example.inventory_manager.repository.SampleInventoryRepository;
import com.example.inventory_manager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Value("${inventory.repository.type:memory}")
    private String repositoryType;

    @Bean
    public InventoryRepository inventoryRepository() {
        switch (repositoryType.toLowerCase()) {
            case "csv":
                return new CsvInventoryRepository();
            case "memory":
                return new SampleInventoryRepository();
            default:
                throw new IllegalArgumentException(
                        "Invalid repository type: " + repositoryType +
                                ". Supported types are: 'csv', 'memory'");
        }
    }
}
