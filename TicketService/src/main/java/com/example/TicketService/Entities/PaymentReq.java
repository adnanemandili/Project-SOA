package com.example.TicketService.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReq {
    private PaymentMethod paymentMethod;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private BigDecimal amount;
}
