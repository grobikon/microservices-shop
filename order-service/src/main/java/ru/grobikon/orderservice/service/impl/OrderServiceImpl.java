package ru.grobikon.orderservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import ru.grobikon.orderservice.dto.InventoryResponse;
import ru.grobikon.orderservice.dto.OrderLineItemsDto;
import ru.grobikon.orderservice.dto.OrderRequest;
import ru.grobikon.orderservice.dto.OrderResponse;
import ru.grobikon.orderservice.model.Order;
import ru.grobikon.orderservice.model.OrderLineItems;
import ru.grobikon.orderservice.repository.OrderRepository;
import ru.grobikon.orderservice.service.OrderService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final WebClient webClient;

    public OrderServiceImpl(OrderRepository repository, WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    @Override
    public void placeOrder(OrderRequest request) {
        log.debug("Start placeOrder request: {}", request);

        var order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        var orderLineItems = request.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);
        var skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //Call Inventory Service, and place order if product is in stock
        var inventoryResponsesArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        var allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

        if (!allProductsInStock) {
            throw new IllegalArgumentException("Товара нет на складе, повторите попытку позже");
        }

        log.debug("Start save data order: {}", order);
        repository.save(order);
        log.debug("Order is saved {}", order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        var order = repository.findAll();

        return null;
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        var orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;
    }
}
