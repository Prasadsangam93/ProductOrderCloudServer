package com.microservice.OrderService.service;

import com.microservice.OrderService.client.PaymentService;
import com.microservice.OrderService.client.ProductService;
import com.microservice.OrderService.entity.Order;
import com.microservice.OrderService.model.OrderRequest;
import com.microservice.OrderService.model.OrderResponse;
import com.microservice.OrderService.model.PaymentMode;
import com.microservice.OrderService.model.ProductDetails;
import com.microservice.OrderService.repository.OrderRepository;
import com.microservice.PaymentService.entity.PaymentDetails;
import com.microservice.PaymentService.model.PaymentRequest;
import com.microservice.PaymentService.model.PaymentResponse;
import com.microservice.ProductService.model.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements  OrderService {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override


    public Map<String, Object> placeOrder(OrderRequest orderRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("Before placing order");

            // Check product availability and reduce quantity
            productService.reducedQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
            log.info("After checking product availability for product: {}", orderRequest.getProductId());

            Order order = new Order();

            // Set order details from the request
            order.setProductId(orderRequest.getProductId());
            order.setQuantity(orderRequest.getQuantity());
            order.setPrice(orderRequest.getAmount());
            log.info("order price :{}", orderRequest.getAmount());
            order.setOrderDate(Instant.now());
            order.setOrderStatus("CREATED"); // Corrected order status

            // Save the order in the database
            Order savedOrder = orderRepository.save(order);
            log.info("Order placed successfully with ID: {}", savedOrder.getOrderId());

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setOrderId(savedOrder.getOrderId()); // Use the saved order ID
            paymentRequest.setPaymentMode(orderRequest.getPaymentMode());
            paymentRequest.setAmount(orderRequest.getAmount());
            // Save the payment request
            PaymentRequest savedPaymentRequest = paymentService.doPayment(paymentRequest);
            log.info("PaymentRequest saved successfully with ID: {}", savedPaymentRequest.getOrderId());


            // Prepare the response
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Order Details Saved Successfully!");
            response.put("orderDetails", savedOrder); // Return the saved order

        } catch (Exception e) {
            log.error("Error while placing order: {}", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Failed to place order: " + e.getMessage());
        }

        return response;
    }

    @Override


    public Map<String, Object> getOrderDetails(Long orderId) {
        Map<String, Object> response = new HashMap<>();

        // Find the order by ID, using Optional to avoid NullPointerExceptions
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get(); // Safely retrieve the Order

            // Create an OrderResponse object
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getOrderId());
            orderResponse.setAmount(order.getPrice());
            orderResponse.setOrderStatus(order.getOrderStatus());
            orderResponse.setProductId(order.getProductId());
            orderResponse.setQuantity(order.getQuantity());
            orderResponse.setOrderDate(order.getOrderDate());
            orderResponse.setPaymentMode(order.getPaymentMode());
            log.info("Order Details with id:{}", orderResponse);


            log.info("orderId : {}", order.getOrderId());
            ResponseEntity<Map<String, Object>> productDetailsResponse = productService.getPaymentDetails(order.getProductId());
            Map<String, Object> productDetailsMap = productDetailsResponse.getBody();
            log.info("productDetailsMap : {}",productDetailsMap);
            if (productDetailsMap != null) {
                // Create a ProductDetails object from the map
                ProductDetails productDetails = new ProductDetails();

                // Assuming the keys in the map correspond to the fields in ProductDetails
                productDetails.setProductId((Long) order.getProductId());
                productDetails.setProductName((String) productDetailsMap.get("productName"));
                productDetails.setPrice((Double) productDetailsMap.get("price"));
                productDetails.setQuantity((int) productDetailsMap.get("quantity"));

                // Set the product details in the order response
                orderResponse.setProductDetails(productDetails);
            }



                // Add the order response to the response map
                response.put("orderDetails", orderResponse);
                response.put("success", true); // Indicate success

        } else {
            // Handle the case where the order is not found
            response.put("error", "Order not found.");
            response.put("success", false); // Indicate failure
        }

        return response;
    }
}






