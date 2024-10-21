package com.microservice.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProductDetails {

    private String productName;
    private Long productId;
    private int quantity;
    private Double price;
}
