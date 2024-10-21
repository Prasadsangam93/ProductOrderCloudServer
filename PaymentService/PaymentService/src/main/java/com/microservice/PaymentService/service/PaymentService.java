package com.microservice.PaymentService.service;

import com.microservice.PaymentService.model.PaymentRequest;

import java.util.Map;


public interface PaymentService {

    Map<String, Object> doPayment(PaymentRequest paymentRequest);

    Map<String, Object> getPaymentDetails(Long orderId);





}