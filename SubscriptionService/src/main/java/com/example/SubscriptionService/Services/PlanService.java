package com.example.SubscriptionService.Services;

import com.example.SubscriptionService.Entities.PlanModel;
import com.example.SubscriptionService.Reposetories.PlanRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Transactional
public class PlanService {

    private final PlanRepo planRepository;

    @Autowired
    public PlanService(PlanRepo planRepository) {
        this.planRepository = planRepository;
    }

    public PlanModel createPlan(PlanModel plan) {
        if (planRepository.existsByName(plan.getName())) {
            throw new IllegalArgumentException("Plan with this name already exists");
        }
        return planRepository.save(plan);
    }

    public PlanModel updatePlan(Long id, PlanModel planDetails) {
        PlanModel plan = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));

        if (!plan.getName().equals(planDetails.getName()) &&
                planRepository.existsByName(planDetails.getName())) {
            throw new IllegalArgumentException("Plan name already exists");
        }

        plan.setName(planDetails.getName());
        plan.setDescription(planDetails.getDescription());
        plan.setFeatures(planDetails.getFeatures());
        plan.setMonthlyPrice(planDetails.getMonthlyPrice());
        plan.setAnnualPrice(planDetails.getAnnualPrice());
        plan.setStripePriceIdMonthly(planDetails.getStripePriceIdMonthly());
        plan.setStripePriceIdAnnual(planDetails.getStripePriceIdAnnual());
        plan.setActive(planDetails.isActive());

        return planRepository.save(plan);
    }

    public List<PlanModel> getAllPlans() {
        return planRepository.findAll();
    }

    public List<PlanModel> getActivePlans() {
        return planRepository.findByActive(true);
    }

    public PlanModel getPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
    }

    public PlanModel getPlanByName(String name) {
        return planRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
    }

    public void deletePlan(Long id) {
        PlanModel plan = planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        plan.setActive(false);
        planRepository.save(plan);
    }
}
