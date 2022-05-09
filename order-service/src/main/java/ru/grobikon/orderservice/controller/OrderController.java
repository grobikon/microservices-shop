package ru.grobikon.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.grobikon.orderservice.dto.OrderRequest;
import ru.grobikon.orderservice.dto.OrderResponse;
import ru.grobikon.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest request) {
        service.placeOrder(request);
        return "Заказ размещен успешно - Order placed Successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return service.getAllOrders();
    }

}
