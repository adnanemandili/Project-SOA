package com.example.TicketService.Controllers;

import com.example.TicketService.Entities.Ticket;
import com.example.TicketService.Entities.TicketReq;
import com.example.TicketService.Services.CustomerService;
import com.example.TicketService.Services.TicketService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
//@PreAuthorize("hasRole('client_passenger')"
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
//    private final CustomerService service;

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable UUID id) {
        return ticketService.findById(id);
    }

    @PostMapping
    public void createTicket(@RequestBody TicketReq ticket, Authentication auth, HttpSession session) {
        ticketService.saveTicket(ticket,auth,session);
        System.out.println(session.getAttribute("ticket"));
    }

    @PutMapping
    public void updateTicket(@RequestBody Ticket ticket) {
        ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping("/{id}/cancel")
    public void cancelTicket(@PathVariable UUID id) {
        ticketService.cancelTicket(id);
    }
    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.Tickets();
    }
}

