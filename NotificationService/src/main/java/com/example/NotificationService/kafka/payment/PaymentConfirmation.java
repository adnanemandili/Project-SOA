package com.example.NotificationService.kafka.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentConfirmation(
        String ticketId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
