package com.microservice.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentResponse {

    private Long paymentId;
    private Long orderId;
    private String status;
    private PaymentMode paymentMode;
    private Double amount;
    private Instant paymentDate;



}
