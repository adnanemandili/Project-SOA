package com.example.SubscriptionService.Reposetories;

import com.example.SubscriptionService.Entities.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepo extends JpaRepository<PlanModel,Long> {
    Optional<PlanModel> findByName(String name);
    List<PlanModel> findByActive(boolean active);
    boolean existsByName(String name);
}
