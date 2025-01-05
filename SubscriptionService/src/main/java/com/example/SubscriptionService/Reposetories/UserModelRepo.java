package com.example.SubscriptionService.Reposetories;

import com.example.SubscriptionService.Entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserModelRepo extends JpaRepository<UserModel, UUID> {
}
