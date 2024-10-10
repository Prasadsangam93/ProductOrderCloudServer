package com.microservice.ProductService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {


    private String productName;
    private  Double price;
    private  int quantity;

}
