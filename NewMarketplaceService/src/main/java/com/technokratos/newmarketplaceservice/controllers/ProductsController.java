package com.technokratos.newmarketplaceservice.controllers;

import com.technokratos.newmarketplaceservice.enums.MarketplaceServiceUrl;
import com.technokratos.newmarketplaceservice.dto.OrderDto;
import com.technokratos.newmarketplaceservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = MarketplaceServiceUrl.PRODUCTS.getUrl();

    @GetMapping("/products")
    public ResponseEntity<List<OrderDto>> getAllProducts() {
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        return restTemplate.postForEntity(url, product, ProductDto.class);
    }

    @PutMapping("/products/{product-id}")
    public void updateProduct(@PathVariable("product-id") Long productId, @RequestBody ProductDto product) {
        restTemplate.put(url, product, productId);
    }

    @DeleteMapping("/products/{product-id}")
    public void deleteProduct(@PathVariable("product-id") Long productId) {
        restTemplate.delete(url + "/" + productId);
    }

}
