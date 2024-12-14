package com.example.TicketService.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ticketId;
    @Column
    private String passengerName;
    @Column
    private String source;
    @Column
    private String destination;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
//    @JsonIgnore
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private TStatus Status;
}
