package com.microservice.OrderService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {


    private Long paymentId;
    private PaymentMode paymentMode;
    private String paymentStatus;
    private Instant paymentDate;

}
