package com.example.TicketService.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.*;


@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
        @Id
        private UUID id;
        @NotNull(message = "username is required")
        private String username;
        @NotNull(message = "Firstname is required")
        private String firstname;
        @NotNull(message = "Lastname is required")
        private String lastname;
        @NotNull(message = "Email is required")
        @Email(message = "The customer email is not correctly formatted")
        private String email;

//        @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//        private List<Ticket> tickets;

//        public Customer(UUID id, @NotNull(message = "username is required") String username, @NotNull(message = "Firstname is required") String firstname, @NotNull(message = "Lastname is required") String lastname, @NotNull(message = "Email is required") String email) {
//                this.id = id;
//                this.username = username;
//                this.firstname = firstname;
//                this.lastname = lastname;
//                this.email = email;
//        }
}
