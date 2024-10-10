package com.microservice.ProductService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    private String productName;
    private  Double price;
    private  int quantity;


}
