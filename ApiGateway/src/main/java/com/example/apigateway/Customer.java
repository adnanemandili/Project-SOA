package com.example.apigateway;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record Customer(
        UUID id,
        @NotNull(message = "username is required") String username,
        @NotNull(message = "Firstname is required") String firstname,
        @NotNull(message = "Lastname is required") String lastname,
        @NotNull(message = "Email is required") @Email(message = "The customer email is not correctly formatted") String email
) {}
