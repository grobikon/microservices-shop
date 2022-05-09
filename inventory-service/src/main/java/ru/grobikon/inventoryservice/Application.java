package ru.grobikon.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import ru.grobikon.inventoryservice.model.Inventory;
import ru.grobikon.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            var inventory1 = createInventory("iphone_13", 100);
            var inventory2 = createInventory("iphone_13_red", 0);

            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
        };
    }

    private Inventory createInventory(String skuCode, Integer quantity) {
        var inventory = new Inventory();
        inventory.setSkuCode(skuCode);
        inventory.setQuantity(quantity);
        return inventory;
    }
}
