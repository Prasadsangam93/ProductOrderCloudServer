package com.microservice.PaymentService.controller;
import com.microservice.PaymentService.model.PaymentRequest;
import com.microservice.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments") // Base path for all endpoints in this controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<Map<String, Object>> doPayment(@RequestBody PaymentRequest paymentRequest) {
        Map<String, Object> response = paymentService.doPayment(paymentRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getPaymentDetails(@PathVariable Long orderId) {
        Map<String, Object> result = paymentService.getPaymentDetails(orderId);
        return ResponseEntity.ok().body(result);
    }
}
