package com.example.SubscriptionService.Controllers;

import com.example.SubscriptionService.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/subscription-service/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final UserService customerService;
    @GetMapping
//    @PreAuthorize("hasRole('client_passenger')")
    public String hello2(Authentication authentication) {
        customerService.createUser(authentication);
        return "Hello from Spring boot & Keycloak - ADMIN";
    }
}