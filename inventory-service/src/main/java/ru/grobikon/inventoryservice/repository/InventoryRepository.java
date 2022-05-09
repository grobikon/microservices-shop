package ru.grobikon.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grobikon.inventoryservice.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
