package com.example.TicketService.Services;

import com.example.TicketService.Entities.Payment;
import com.example.TicketService.Entities.PaymentReq;
import com.example.TicketService.Entities.TStatus;
import com.example.TicketService.Entities.Ticket;
import com.example.TicketService.Notification.NotificationProducer;
import com.example.TicketService.Notification.PaymentNotificationRequest;
import com.example.TicketService.Repos.PaymentRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo repository;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentReq request, HttpSession session) {
        Payment payment=new Payment();
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setCreatedDate(request.getCreatedDate());
        payment.setLastModifiedDate(request.getLastModifiedDate());
        payment.setAmount(request.getAmount());
        Ticket ticket=(Ticket) session.getAttribute("ticket");
        if (ticket == null) {
            throw new IllegalStateException("No ticket found in session. Cannot proceed with payment.");
        }
        ticket.setStatus(TStatus.TERMINE);
        payment.setTicket(ticket);
        repository.save(payment);

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        ticket.getTicketId().toString(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        ticket.getCustomer().getFirstname(),
                        ticket.getCustomer().getLastname(),
                        ticket.getCustomer().getEmail()
                )
        );
        session.removeAttribute("ticket");
        return payment.getId();
    }
}
