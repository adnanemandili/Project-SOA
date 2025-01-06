package com.example.SubscriptionService.Controllers;

import com.example.SubscriptionService.Entities.BillingCycle;
import com.example.SubscriptionService.Entities.SubscriptionModel;
import com.example.SubscriptionService.Entities.SubscriptionReq;
import com.example.SubscriptionService.Entities.SubscriptionStatus;
import com.example.SubscriptionService.Services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/subscription-service/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionModel> createSubscription(
            Authentication auth,
            @RequestBody SubscriptionReq req) {

        SubscriptionModel subscription = subscriptionService.createSubscription(auth, req.planId(),req.billingCycle());
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    @PatchMapping("/{stripeSubscriptionId}/status")
    public ResponseEntity<SubscriptionModel> updateStatus(
            @PathVariable String stripeSubscriptionId,
            @RequestParam SubscriptionStatus status) {
        SubscriptionModel subscription = subscriptionService
                .updateSubscriptionStatus(stripeSubscriptionId, status);
        return ResponseEntity.ok(subscription);
    }

    @PostMapping("/{stripeSubscriptionId}/cancel")
    public ResponseEntity<SubscriptionModel> cancelSubscription(
            @PathVariable String stripeSubscriptionId) {
        SubscriptionModel subscription = subscriptionService
                .cancelSubscription(stripeSubscriptionId);
        return ResponseEntity.ok(subscription);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<SubscriptionModel>> getUserSubscriptions(
//            @PathVariable UUID userId) {
//        List<SubscriptionModel> subscriptions = subscriptionService
//                .getUserSubscriptions(userId);
//        return ResponseEntity.ok(subscriptions);
//    }
}