package com.microservice.OrderService.client;


import com.microservice.PaymentService.entity.PaymentDetails;
import com.microservice.PaymentService.model.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name="PAYMENTSERVICE/payments")
public interface PaymentService {

    @PostMapping("/doPayment")

    public PaymentRequest doPayment(@RequestBody PaymentRequest paymentRequest) ;

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderDetails(@PathVariable Long orderId);

}
