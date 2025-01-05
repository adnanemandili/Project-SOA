package com.example.SubscriptionService.Services;

import com.example.SubscriptionService.Entities.UserModel;
import com.example.SubscriptionService.Reposetories.UserModelRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import com.example.SubscriptionService.Authservice.JwtAuthConverter;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    private UserModelRepo userModelRepo;
    private JwtAuthConverter jwtAuthConverter;

    @Autowired
    public UserService(UserModelRepo userModelRepo, JwtAuthConverter jwtAuthConverter) {
        this.userModelRepo = userModelRepo;
        this.jwtAuthConverter = jwtAuthConverter;
    }

    public UserModel getUserById(UUID id) {
        return userModelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public void createUser(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        if (existsById(jwtAuthConverter.getAthenaticatedUserId(jwt))){
            System.out.println(jwtAuthConverter.extractCustomerInfoFromJwt(jwt).toString()+" deja exist") ;
        }
        else{
            UserModel customer=jwtAuthConverter.extractCustomerInfoFromJwt(jwt);
            userModelRepo.save(customer);
            System.out.println(customer.getId().toString()+ " cet client et enregistre");
        }
    }





    public boolean existsById(UUID id) {
        return userModelRepo.findById(id)
                .isPresent();
    }
}
