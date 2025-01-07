package com.example.chatbotservice.service;

import com.example.chatbotservice.entity.Document;
import com.example.chatbotservice.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final RestTemplate restTemplate;

    @Value("${chatbot.api.url}")
    private String chatbotApiUrl;

    public Map<String, Object> uploadDocument(MultipartFile file) {
        // Sauvegarde du document en base
        Document doc = new Document();
        doc.setFilename(file.getOriginalFilename());
        doc.setContentType(file.getContentType());
        doc.setFileSize(file.getSize());
        documentRepository.save(doc);

        // Upload vers l'API externe
        String url = chatbotApiUrl + "/upload/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = 
            new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = 
            restTemplate.postForEntity(url, requestEntity, Map.class);
        
        return response.getBody();
    }
}