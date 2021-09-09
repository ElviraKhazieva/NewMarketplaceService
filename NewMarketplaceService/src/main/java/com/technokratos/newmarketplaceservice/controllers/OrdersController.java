package com.technokratos.newmarketplaceservice.controllers;

import com.technokratos.newmarketplaceservice.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    private static final String PATH = "orders";

    private static final String ARTICLE_PARAM = "article={article}";

    private static final String EMAIL_PARAM = "email={email}";

    private static final String DATE_RANGE_PARAM = "startDate={startDate}&endDate={endDate}";

    @GetMapping(value = "/orders", params = {"email"})
    public ResponseEntity<OrderDto[]> getOrdersByEmail(@RequestParam("email") String email) {
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(url).path(PATH).query(EMAIL_PARAM)
                .buildAndExpand(email).toUriString(), OrderDto[].class);
    }

    @GetMapping(value = "/orders", params = {"article"})
    public ResponseEntity<OrderDto[]> getOrdersContainsProductArticle(@RequestParam("article") String article) {
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(url).path(PATH).query(ARTICLE_PARAM)
                .buildAndExpand(article).toUriString(), OrderDto[].class);

    }

    @GetMapping(value = "/orders", params = {"startDate", "endDate"})
    public ResponseEntity<List<OrderDto>> getOrdersInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        OrderDto[] ordersInRange = restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(url).path(PATH).query(DATE_RANGE_PARAM)
                .buildAndExpand(startDate, endDate).toUriString(), OrderDto[].class);
        return ResponseEntity.ok(Arrays.asList(ordersInRange));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto order) {
         return restTemplate.postForEntity(url, order, OrderDto.class);
    }

}

