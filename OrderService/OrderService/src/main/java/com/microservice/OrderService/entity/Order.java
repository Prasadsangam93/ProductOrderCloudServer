package com.microservice.OrderService.entity;



import com.microservice.OrderService.model.ProductDetails;
import com.microservice.PaymentService.entity.PaymentDetails;
import com.microservice.PaymentService.model.PaymentResponse;
import com.microservice.ProductService.model.ProductResponse;
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
    private  Double Price;
    private Instant orderDate;
    private  String orderStatus;
    private int quantity;
   private  String paymentMode;
//    private PaymentDetails paymentDetails;
//   private ProductDetails productDetails;
//

}
