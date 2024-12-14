package com.example.TicketService.Repos;

import com.example.TicketService.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {
}
