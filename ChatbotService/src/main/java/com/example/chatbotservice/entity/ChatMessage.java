package com.example.chatbotservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String question;
    
    @Column(columnDefinition = "TEXT")
    private String answer;
    
    private LocalDateTime timestamp = LocalDateTime.now();
}