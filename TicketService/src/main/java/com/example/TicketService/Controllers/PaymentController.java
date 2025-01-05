package com.example.TicketService.Controllers;

import com.example.TicketService.Entities.PaymentReq;
import com.example.TicketService.Services.PaymentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets-service/payments")
//@PreAuthorize("hasRole('client_passenger')")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentReq request,
            HttpSession session
    ) {
        return ResponseEntity.ok(this.service.createPayment(request,session));
    }
}