package com.example.inventory_manager;

import com.example.inventory_manager.view.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication implements CommandLineRunner {

	@Autowired
	private Inventory inventory;

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		inventory.run();
	}
}
