package com.microservice.PaymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum PaymentMode {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD
}
