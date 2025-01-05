package com.example.SubscriptionService.Controllers;

import com.example.SubscriptionService.Entities.PlanModel;
import com.example.SubscriptionService.Services.PlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscription-service/plans")
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanModel> createPlan(@Valid @RequestBody PlanModel plan) {
        PlanModel newPlan = planService.createPlan(plan);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanModel> updatePlan(@PathVariable Long id,
                                           @Valid @RequestBody PlanModel plan) {
        PlanModel updatedPlan = planService.updatePlan(id, plan);
        return ResponseEntity.ok(updatedPlan);
    }

    @GetMapping
    public ResponseEntity<List<PlanModel>> getAllPlans() {
        List<PlanModel> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/active")
    public ResponseEntity<List<PlanModel>> getActivePlans() {
        List<PlanModel> plans = planService.getActivePlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanModel> getPlanById(@PathVariable Long id) {
        PlanModel plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok().build();
    }
}