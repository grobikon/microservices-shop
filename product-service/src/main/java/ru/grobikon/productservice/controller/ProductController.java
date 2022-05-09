package ru.grobikon.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.grobikon.productservice.dto.ProductRequest;
import ru.grobikon.productservice.dto.ProductResponse;
import ru.grobikon.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        service.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

}
