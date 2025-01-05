package com.example.TicketService.Controllers;

import com.example.TicketService.Services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/tickets-service/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping
//    @PreAuthorize("hasRole('client_passenger')")
    public String hello2(Authentication authentication) {
        customerService.createCustomer(authentication);
        return "Hello from Spring boot & Keycloak - ADMIN";
    }
}
