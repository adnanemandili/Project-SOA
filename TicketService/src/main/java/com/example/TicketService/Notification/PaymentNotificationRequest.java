package com.example.TicketService.Notification;

import com.example.TicketService.Entities.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentNotificationRequest(
        String ticketId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}