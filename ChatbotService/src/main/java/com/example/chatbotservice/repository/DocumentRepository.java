package com.example.chatbotservice.repository;

import com.example.chatbotservice.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}