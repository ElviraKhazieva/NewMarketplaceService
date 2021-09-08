package com.technokratos.newmarketplaceservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Long id;

    private List<Product> products;

    private LocalDateTime creation;

    private String customerEmail;

    private Integer number;

}
