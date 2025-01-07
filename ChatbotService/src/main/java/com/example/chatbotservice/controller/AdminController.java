package com.example.chatbotservice.controller;

import com.example.chatbotservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/chat-bot-service/")
@RequiredArgsConstructor
public class AdminController {
    private final DocumentService documentService;

    @PostMapping("/documents/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(documentService.uploadDocument(file));
    }
}