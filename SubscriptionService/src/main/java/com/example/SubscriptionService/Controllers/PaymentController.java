package com.example.SubscriptionService.Controllers;

import com.example.SubscriptionService.Entities.PaymentReqest;
import com.example.SubscriptionService.Services.PaymentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subscription-service/payments")
//@PreAuthorize("hasRole('client_passenger')")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<?> createPayment(
            @RequestBody @Valid PaymentReqest request,
            Authentication auth
    ) {
        return ResponseEntity.ok(this.service.createPayment(request,auth));
    }
}
