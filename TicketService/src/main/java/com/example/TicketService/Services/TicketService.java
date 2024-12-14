package com.example.TicketService.Services;

import com.example.TicketService.Authservice.JwtAuthConverter;
import com.example.TicketService.Entities.Customer;
import com.example.TicketService.Entities.TStatus;
import com.example.TicketService.Entities.Ticket;
import com.example.TicketService.Entities.TicketReq;
import com.example.TicketService.Repos.TicketRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final JwtAuthConverter jwtAuthConverter;

    public Ticket findById(UUID id) {
        return ticketRepo.findById(id).orElse(null);
    }

    @Transactional
    public void saveTicket(TicketReq ticket, Authentication auth, HttpSession session) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        Customer customer=jwtAuthConverter.extractCustomerInfoFromJwt(jwt);
        Ticket ticket1=new Ticket();
        ticket1.setPassengerName(customer.getFirstname()+" "+customer.getLastname());
        ticket1.setSource(ticket.getSource());
        ticket1.setDestination(ticket.getDestination());
        ticket1.setCustomer(customer);
        ticket1.setStatus(TStatus.EN_ATTENTE);
        ticketRepo.save(ticket1);

        session.setAttribute("ticket", ticket1);
    }

    @Transactional
    public void updateTicket(Ticket ticket) {
        if (existsById(ticket.getTicketId())) {
            ticketRepo.save(ticket); // save acts as update if entity exists
        }
    }

    @Transactional
    public void deleteTicket(UUID id) {
        ticketRepo.deleteById(id);
    }

    @Transactional
    public void cancelTicket(UUID ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);
        if (ticket != null) {
            ticket.setStatus(TStatus.ANNULE);
            ticketRepo.save(ticket);
        } else {
            System.out.println("Ticket not found!");
        }
    }

    public List<Ticket> Tickets (){
        return ticketRepo.findAll();
    }

    public boolean existsById(UUID id) {
        return ticketRepo.findById(id)
                .isPresent();
    }
}
