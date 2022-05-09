package ru.grobikon.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grobikon.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
