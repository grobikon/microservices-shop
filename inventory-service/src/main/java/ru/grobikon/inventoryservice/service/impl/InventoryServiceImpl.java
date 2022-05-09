package ru.grobikon.inventoryservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grobikon.inventoryservice.dto.InventoryResponse;
import ru.grobikon.inventoryservice.repository.InventoryRepository;
import ru.grobikon.inventoryservice.service.InventoryService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.debug("Start isInStock skuCode: {}", skuCode);
        return repository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
