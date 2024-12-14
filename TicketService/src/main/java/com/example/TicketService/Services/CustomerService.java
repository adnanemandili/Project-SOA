package com.example.TicketService.Services;

import com.example.TicketService.Authservice.JwtAuthConverter;
import com.example.TicketService.Entities.Customer;
import com.example.TicketService.Repos.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final JwtAuthConverter jwtAuthConverter;


    public void createCustomer(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        if (existsById(jwtAuthConverter.getAthenaticatedUserId(jwt))){
            System.out.println(jwtAuthConverter.extractCustomerInfoFromJwt(jwt).toString()+" deja exist") ;
        }
        else{
            Customer customer=jwtAuthConverter.extractCustomerInfoFromJwt(jwt);
            customerRepo.save(customer);
            System.out.println(customer.getId().toString()+ " cet client et enregistre");
        }
    }





    public boolean existsById(UUID id) {
        return customerRepo.findById(id)
                .isPresent();
    }
}
