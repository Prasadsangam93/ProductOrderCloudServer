package com.microservice.PaymentService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="paymentDetails_tab")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  Long paymentId;
    private  Long  orderId;
    private Instant paymentDate;
    private String  paymentMode;
    private  String status;
    private  Double amount;

}
