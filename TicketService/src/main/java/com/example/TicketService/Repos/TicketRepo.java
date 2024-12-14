package com.example.TicketService.Repos;

import com.example.TicketService.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepo extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByCustomer_Id(UUID customerId);
}
