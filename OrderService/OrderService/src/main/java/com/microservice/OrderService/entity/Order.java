package com.microservice.OrderService.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_tab")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long orderId;
    private  Long productId;
    private  Double price;
    private Instant orderDate;
    private  String orderStatus;
    private int quantity;

}
