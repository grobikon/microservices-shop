package ru.grobikon.inventoryservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grobikon.inventoryservice.repository.InventoryRepository;
import ru.grobikon.inventoryservice.service.InventoryService;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        log.debug("Start isInStock skuCode: {}", skuCode);
        return repository.findBySkuCode(skuCode).isPresent();
    }
}
