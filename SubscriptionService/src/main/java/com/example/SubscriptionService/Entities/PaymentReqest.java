package com.example.SubscriptionService.Entities;

public record PaymentReqest(Long subscriptionId,
                            Double amount,
                            String stripePaymentIntentId,
                            PaymentStatus paymentStatus,
                            PaymentMethod paymentMethod
) {
}
