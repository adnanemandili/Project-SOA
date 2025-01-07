package com.example.chatbotservice.controller;

import com.example.chatbotservice.dto.ChatRequest;
import com.example.chatbotservice.dto.ChatResponse;
import com.example.chatbotservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-bot-service/")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

//    @PostMapping("/ask")
//    public ResponseEntity<ChatResponse> ask(@RequestBody ChatRequest request) {
//        return ResponseEntity.ok(chatService.processQuestion(request));
//    }
    @PostMapping(value = "/ask", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ChatResponse askQuestion(@RequestParam("question") String question) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setQuestion(question);
        return chatService.processQuestion(chatRequest);
    }
}