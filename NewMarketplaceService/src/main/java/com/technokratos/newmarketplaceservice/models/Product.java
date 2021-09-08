package com.technokratos.newmarketplaceservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;

    private String name;

    private Double price;

    private String article;

    private Boolean isDeleted;

    private List<Order> orders;

    public Boolean isDeleted() {
        return isDeleted;
    }

}
