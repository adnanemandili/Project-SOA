package com.example.TicketService.Entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@AllArgsConstructor
@NoArgsConstructor

public class TicketReq {
    private String source;
    private String destination;
}
