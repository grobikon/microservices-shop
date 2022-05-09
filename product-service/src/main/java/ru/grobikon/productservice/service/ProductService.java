package ru.grobikon.productservice.service;

import ru.grobikon.productservice.dto.ProductRequest;
import ru.grobikon.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();
}
