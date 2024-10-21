package com.microservice.ProductService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product_tab")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long productId;
    private String productName;
    private  double price;
    private  int  quantity;


}
