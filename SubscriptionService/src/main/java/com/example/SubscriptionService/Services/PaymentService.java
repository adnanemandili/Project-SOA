package com.example.SubscriptionService.Services;

import com.example.SubscriptionService.Authservice.JwtAuthConverter;
import com.example.SubscriptionService.Entities.*;
import com.example.SubscriptionService.Reposetories.PaymentRepo;
import com.example.SubscriptionService.Reposetories.SubscriptionRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepo paymentRepository;
    private final SubscriptionRepo subscriptionRepo;
    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public PaymentService(PaymentRepo paymentRepository ,SubscriptionRepo subscriptionRepo , JwtAuthConverter jwtAuthConverter) {

        this.paymentRepository = paymentRepository;
        this.subscriptionRepo= subscriptionRepo;
        this.jwtAuthConverter=jwtAuthConverter;
    }

    public PaymentModel createPayment(PaymentReqest paymentReqest,Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        UserModel customer=jwtAuthConverter.extractCustomerInfoFromJwt(jwt);
        PaymentModel payment = new PaymentModel();
        payment.setUser(customer);
        SubscriptionModel subscription=subscriptionRepo.findById(paymentReqest.subscriptionId()).get();
        payment.setSubscription(subscription);
        payment.setAmount(paymentReqest.amount());
        payment.setStripePaymentIntentId(payment.getStripePaymentIntentId());
        payment.setStatus(paymentReqest.paymentStatus());
        payment.setPaymentMethod(paymentReqest.paymentMethod());

        return paymentRepository.save(payment);
    }

//    public List<PaymentModel> getUserPayments(UUID userId) {
//        return paymentRepository.findByUser(userId);
//    }

    public PaymentModel updatePaymentStatus(Long paymentId, PaymentStatus status) {
        PaymentModel payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}