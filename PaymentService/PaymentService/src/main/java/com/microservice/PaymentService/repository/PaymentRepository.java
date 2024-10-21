package com.microservice.PaymentService.repository;

import com.microservice.PaymentService.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {

    public PaymentDetails findByOrderId(Long orderId);

}
