package com.example.SubscriptionService.Reposetories;

import com.example.SubscriptionService.Entities.SubscriptionModel;
import com.example.SubscriptionService.Entities.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepo extends JpaRepository<SubscriptionModel,Long> {
//    List<SubscriptionModel> findByUser_UserId(UUID userId);
    List<SubscriptionModel> findByStatus(SubscriptionStatus status);
    Optional<SubscriptionModel> findByStripeSubscriptionId(String stripeSubscriptionId);
    boolean existsByUserIdAndStatus(UUID userId, SubscriptionStatus status);
}
