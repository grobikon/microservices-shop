package ru.grobikon.orderservice.service;


import ru.grobikon.orderservice.dto.OrderRequest;
import ru.grobikon.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();
}
