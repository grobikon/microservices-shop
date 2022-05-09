package ru.grobikon.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.grobikon.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
