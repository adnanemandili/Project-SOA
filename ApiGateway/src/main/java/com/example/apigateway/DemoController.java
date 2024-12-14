package com.example.apigateway;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")

public class DemoController {


    @GetMapping
    @PreAuthorize("hasRole('client_driver')")
    public String hello() {
        return "Hello from Spring boot & Keycloak Driver";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('client_admin')")
    public String hello2() {

        return "Hello from Spring boot & Keycloak - ADMIN";
    }
    private final JwtAuthConverter jwtAuthConverter;

    public DemoController(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    // Example endpoint to get customer information
    @GetMapping("/info")
    @PreAuthorize("hasRole('client_admin')")
    public Customer getCustomerInfo(Authentication authentication) {
        // The Authentication object will contain the Jwt token after authentication
        Jwt jwt = (Jwt) authentication.getPrincipal();

        // Call the extractCustomerInfoFromJwt method to extract customer info from the JWT
        Customer customer = jwtAuthConverter.extractCustomerInfoFromJwt(jwt);

        // Return the customer information
        return customer;
    }
}