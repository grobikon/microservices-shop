package ru.grobikon.inventoryservice.service;

public interface InventoryService {
    boolean isInStock(String skuCode);
}
