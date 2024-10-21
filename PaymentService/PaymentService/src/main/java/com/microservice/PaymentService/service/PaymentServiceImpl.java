package com.microservice.PaymentService.service;

import com.microservice.PaymentService.entity.PaymentDetails;
import com.microservice.PaymentService.model.PaymentRequest;
import com.microservice.PaymentService.model.PaymentResponse;
import com.microservice.PaymentService.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Map<String, Object> doPayment(PaymentRequest paymentRequest) {
        Map<String, Object> result = new HashMap<>();
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails .setOrderId(paymentRequest.getOrderId());
        paymentDetails.setPaymentMode(paymentRequest.getPaymentMode());
        paymentDetails.setAmount(paymentRequest.getAmount());
        paymentDetails.setPaymentDate(Instant.now());
        paymentDetails.setStatus("SUCCESS");

        log.info("Processing payment for order ID: {}", paymentDetails.getOrderId());

        try {
            PaymentDetails savedPayment = paymentRepository.save(paymentDetails);
            result.put("status", HttpStatus.OK.value());
            result.put("message", "Order Details Saved Successfully!");
            result.put("orderDetails", savedPayment);
        } catch (Exception e) {
            log.error("Failed to save payment details: {}", e.getMessage());
            result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.put("message", "Failed to process payment.");
        }

        return result;
    }

    @Override

    public Map<String, Object> getPaymentDetails(Long orderId) {
        Map<String, Object> response = new HashMap<>();
        PaymentDetails paymentDetails = paymentRepository.findByOrderId(orderId);

        if (paymentDetails != null) {
            PaymentResponse paymentResponse = new PaymentResponse();
            // Set payment details into PaymentResponse
            paymentResponse.setPaymentId(paymentDetails.getPaymentId()); // Use the correct getter
            paymentResponse.setOrderId(paymentDetails.getOrderId());
            paymentResponse.setAmount(paymentDetails.getAmount());
            paymentResponse.setPaymentDate(paymentDetails.getPaymentDate()); // Use the correct getter
            paymentResponse.setPaymentMode(paymentDetails.getPaymentMode()); // Use the correct getter
            paymentResponse.setStatus(paymentDetails.getStatus());
            response.put("status", HttpStatus.OK);
            response.put("paymentResponse", paymentResponse);
            return response;
        } else {
            log.error("Payment details not found for order ID: {}", orderId);
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Payment details not found for order ID: " + orderId);
            return response; // Return response instead of throwing an exception
        }
    }
}