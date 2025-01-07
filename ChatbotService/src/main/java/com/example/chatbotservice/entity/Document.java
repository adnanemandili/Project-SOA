package com.example.chatbotservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String filename;
    private String contentType;
    private Long fileSize;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}