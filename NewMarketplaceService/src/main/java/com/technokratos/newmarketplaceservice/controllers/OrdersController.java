package com.technokratos.newmarketplaceservice.controllers;

import com.technokratos.newmarketplaceservice.enums.MarketplaceServiceUrl;
import com.technokratos.newmarketplaceservice.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = MarketplaceServiceUrl.ORDERS.getUrl();

    @GetMapping(value = "/orders", params = {"email"})
    public ResponseEntity<List<OrderDto>> getOrdersByEmail(@RequestParam("email") String email) {
        return restTemplate.exchange(url + "?email={email}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<OrderDto>>() {}, email);
    }

    @GetMapping(value = "/orders", params = {"article"})
    public ResponseEntity<List<OrderDto>> getOrdersContainsProductArticle(@RequestParam("article") String article) {
        return restTemplate.exchange(url + "?article={article}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<OrderDto>>() {}, article);

    }

    @GetMapping(value = "/orders", params = {"startDate", "endDate"})
    public ResponseEntity<List<OrderDto>> getOrdersInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return restTemplate.exchange(url + "?startDate={startDate}&endDate={enddDate}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<OrderDto>>() {}, startDate, endDate);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto order) {
         return restTemplate.postForEntity(url, order, OrderDto.class);
    }
}

