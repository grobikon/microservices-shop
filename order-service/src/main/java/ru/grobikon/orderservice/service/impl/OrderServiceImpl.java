package ru.grobikon.orderservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grobikon.orderservice.dto.OrderLineItemsDto;
import ru.grobikon.orderservice.dto.OrderRequest;
import ru.grobikon.orderservice.dto.OrderResponse;
import ru.grobikon.orderservice.model.Order;
import ru.grobikon.orderservice.model.OrderLineItems;
import ru.grobikon.orderservice.repository.OrderRepository;
import ru.grobikon.orderservice.service.OrderService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
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
