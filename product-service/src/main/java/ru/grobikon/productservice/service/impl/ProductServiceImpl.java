package ru.grobikon.productservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.grobikon.productservice.dto.ProductRequest;
import ru.grobikon.productservice.dto.ProductResponse;
import ru.grobikon.productservice.model.Product;
import ru.grobikon.productservice.repository.ProductRepository;
import ru.grobikon.productservice.service.ProductService;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createProduct(ProductRequest request) {
        log.debug("Start createProduct request: {}", request);

        var product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        log.debug("Start save data product: {}", product);
        repository.save(product);
        log.debug("Product is saved {}", product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = repository.findAll();
        
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
