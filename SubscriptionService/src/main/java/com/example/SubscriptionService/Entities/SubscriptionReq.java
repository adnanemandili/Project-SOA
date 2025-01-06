package com.example.SubscriptionService.Entities;




public record SubscriptionReq(
        Long planId,
        BillingCycle billingCycle
) {
    // Default constructor is not allowed in records
    // You can add static factory methods if needed
}

