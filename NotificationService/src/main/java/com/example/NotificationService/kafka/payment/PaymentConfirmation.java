package com.example.NotificationService.kafka.payment;

import java.math.BigDecimal;
public record PaymentConfirmation(
        String ticketReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
