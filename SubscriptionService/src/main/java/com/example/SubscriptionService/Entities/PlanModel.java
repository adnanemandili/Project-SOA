package com.example.SubscriptionService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "plan_features",
            joinColumns = @JoinColumn(name = "plan_id")
    )
    @Column(name = "feature")
    private List<String> features = new ArrayList<>();

    @Column(name = "monthly_price", nullable = false)
    private Double monthlyPrice;

    @Column(name = "annual_price", nullable = false)
    private Double annualPrice;

    @Column(name = "stripe_price_id_monthly", nullable = false)
    private String stripePriceIdMonthly;

    @Column(name = "stripe_price_id_annual", nullable = false)
    private String stripePriceIdAnnual;

    @Column(nullable = false)
    private boolean active = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
