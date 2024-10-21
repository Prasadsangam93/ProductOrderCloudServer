package com.microservice.PaymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {


    private Long paymentId;
    private  Long orderId;
    private Instant paymentDate;
    private String paymentMode;
    private  String status;
    private  Double amount;


}
