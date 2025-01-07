package com.example.localisation.service;

import com.example.localisation.DTO.BusLocationDTO;
import com.example.localisation.DTO.TrajetDTO;
import com.example.localisation.model.Trajet;
import com.example.localisation.repository.TrajetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajetService {

    private final TrajetRepository trajetRepository;

    public TrajetService(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    public List<TrajetDTO> getAllTrajets() {
        List<Trajet> trajets = trajetRepository.findAll();
        return trajets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TrajetDTO getTrajetById(Long id) {
        Trajet trajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet not found with ID: " + id));
        return convertToDTO(trajet);
    }

    public TrajetDTO createTrajet(Trajet trajet) {
        Trajet savedTrajet = trajetRepository.save(trajet);
        return convertToDTO(savedTrajet);
    }

    public TrajetDTO updateTrajet(Long id, Trajet updatedTrajet) {
        Trajet existingTrajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet not found with ID: " + id));
        existingTrajet.setName(updatedTrajet.getName());
        Trajet savedTrajet = trajetRepository.save(existingTrajet);
        return convertToDTO(savedTrajet);
    }

    public void deleteTrajet(Long id) {
        trajetRepository.deleteById(id);
    }

    TrajetDTO convertToDTO(Trajet trajet) {
        TrajetDTO dto = new TrajetDTO();
        dto.setId(trajet.getId());
        dto.setName(trajet.getName());
        dto.setLocations(
                trajet.getLocations() != null
                        ? trajet.getLocations().stream().map(location -> {
                    BusLocationDTO locationDTO = new BusLocationDTO();
                    locationDTO.setId(location.getId());
                    locationDTO.setLatitude(location.getLatitude());
                    locationDTO.setLongitude(location.getLongitude());
                    return locationDTO;
                }).collect(Collectors.toList())
                        : new ArrayList<>() // Return an empty list if locations is null
        );
        return dto;
    }
}
