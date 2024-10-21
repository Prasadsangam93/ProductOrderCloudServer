package com.microservice.OrderService.model;




import com.microservice.PaymentService.entity.PaymentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private  Long orderId;
    private  Long productId;
    private  int quantity;
    private  Double  amount;
    private Instant orderDate;
    private  String orderStatus;
    private  String paymentMode;
    private PaymentDetails paymentDetails;
    private ProductDetails productDetails;

}
