package com.example.SubscriptionService.Services;

import com.example.SubscriptionService.Entities.*;
import com.example.SubscriptionService.Reposetories.SubscriptionRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepository;
    private final UserService userService;
    private final PlanService planService;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepository,
                               UserService userService,
                               PlanService planService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.planService = planService;
    }

    public SubscriptionModel createSubscription(UUID userId, Long planId,
                                                String stripeSubscriptionId,
                                                BillingCycle billingCycle) {
        UserModel user = userService.getUserById(userId);
        PlanModel plan = planService.getPlanById(planId);

        if (subscriptionRepository.existsByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE)) {
            throw new IllegalStateException("User already has an active subscription");
        }

        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStripeSubscriptionId(stripeSubscriptionId);
        subscription.setBillingCycle(billingCycle);
        subscription.setCurrentPeriodStart(LocalDateTime.now());
        subscription.setCurrentPeriodEnd(calculatePeriodEnd(LocalDateTime.now(), billingCycle));

        return subscriptionRepository.save(subscription);
    }

    public SubscriptionModel updateSubscriptionStatus(String stripeSubscriptionId,
                                                 SubscriptionStatus status) {
        SubscriptionModel subscription = subscriptionRepository
                .findByStripeSubscriptionId(stripeSubscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setStatus(status);
        return subscriptionRepository.save(subscription);
    }

    public SubscriptionModel cancelSubscription(String stripeSubscriptionId) {
        SubscriptionModel subscription = subscriptionRepository
                .findByStripeSubscriptionId(stripeSubscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setCancelAtPeriodEnd(true);
        return subscriptionRepository.save(subscription);
    }

    public List<SubscriptionModel> getUserSubscriptions(UUID userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    private LocalDateTime calculatePeriodEnd(LocalDateTime start, BillingCycle cycle) {
        return cycle == BillingCycle.MONTHLY ?
                start.plusMonths(1) : start.plusYears(1);
    }
}
