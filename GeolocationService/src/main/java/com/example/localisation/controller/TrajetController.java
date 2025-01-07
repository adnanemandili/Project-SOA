package com.example.localisation.controller;

import com.example.localisation.DTO.TrajetDTO;
import com.example.localisation.model.Trajet;
import com.example.localisation.service.TrajetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geolocation-service/trajets")
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    @GetMapping
    public ResponseEntity<List<TrajetDTO>> getAllTrajets() {
        return ResponseEntity.ok(trajetService.getAllTrajets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrajetDTO> getTrajetById(@PathVariable Long id) {
        return ResponseEntity.ok(trajetService.getTrajetById(id));
    }

    @PostMapping
    public ResponseEntity<TrajetDTO> createTrajet(@RequestBody Trajet trajet) {
        return ResponseEntity.ok(trajetService.createTrajet(trajet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrajetDTO> updateTrajet(@PathVariable Long id, @RequestBody Trajet trajet) {
        return ResponseEntity.ok(trajetService.updateTrajet(id, trajet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        trajetService.deleteTrajet(id);
        return ResponseEntity.noContent().build();
    }
}
