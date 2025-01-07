package com.example.chatbotservice.service;

import com.example.chatbotservice.dto.ChatRequest;
import com.example.chatbotservice.dto.ChatResponse;
import com.example.chatbotservice.entity.ChatMessage;
import com.example.chatbotservice.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final RestTemplate restTemplate;

    @Value("${chatbot.api.url}")
    private String chatbotApiUrl;

    public ChatResponse processQuestion(ChatRequest request) {
        // Appel à l'API externe
        String answer = callExternalChatbot(request.getQuestion());
        
        // Sauvegarde en base
        ChatMessage message = new ChatMessage();
        message.setQuestion(request.getQuestion());
        message.setAnswer(answer);
        chatMessageRepository.save(message);
        
        return new ChatResponse(answer);
    }

    private String callExternalChatbot(String question) {
        String url = chatbotApiUrl + "/ask/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("question", question);
        
        HttpEntity<MultiValueMap<String, String>> request = 
            new HttpEntity<>(map, headers);
        
        ResponseEntity<Map> response = 
            restTemplate.postForEntity(url, request, Map.class);
        return (String) response.getBody().get("answer");
    }
}