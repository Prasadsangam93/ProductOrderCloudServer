package com.microservice.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private  Long orderId;
    private  Long productId;
    private  int quantity;
    private  Double  amount;
    private  String paymentMode;

}
