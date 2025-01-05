package com.example.SubscriptionService.Reposetories;

import com.example.SubscriptionService.Entities.PaymentModel;
import com.example.SubscriptionService.Entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepo extends JpaRepository<PaymentModel,Long> {
    List<PaymentModel> findByUserId(UUID userId);
    List<PaymentModel> findBySubscriptionId(Long subscriptionId);
    List<PaymentModel> findByStatus(PaymentStatus status);
}
