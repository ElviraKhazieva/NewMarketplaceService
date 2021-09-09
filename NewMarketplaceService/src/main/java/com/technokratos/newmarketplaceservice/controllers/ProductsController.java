package com.technokratos.newmarketplaceservice.controllers;

import com.technokratos.newmarketplaceservice.dto.OrderDto;
import com.technokratos.newmarketplaceservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ProductsController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${products.url}")
    private String url;

    @GetMapping("/products")
    public ResponseEntity<OrderDto[]> getAllProducts() {
        return restTemplate.getForEntity(url, OrderDto[].class);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        return restTemplate.postForEntity(url, product, ProductDto.class);
    }

    @PutMapping("/products/{product-id}")
    public void updateProduct(@PathVariable("product-id") Long productId, @RequestBody ProductDto product) {
        restTemplate.put(UriComponentsBuilder.fromHttpUrl(url).path("/{product-id}")
                .buildAndExpand(productId).toUriString(), product);
    }

    @DeleteMapping("/products/{product-id}")
    public void deleteProduct(@PathVariable("product-id") Long productId) {
        restTemplate.delete(UriComponentsBuilder.fromHttpUrl(url).path("/{product-id}")
                .buildAndExpand(productId).toUriString());
    }

}
