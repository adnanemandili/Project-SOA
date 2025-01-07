package com.example.localisation.service;

import com.example.localisation.DTO.BusDTO;
import com.example.localisation.DTO.BusLocationDTO;
import com.example.localisation.DTO.TrajetDTO;
import com.example.localisation.model.Bus;
import com.example.localisation.model.BusLocation;
import com.example.localisation.model.Trajet;
import com.example.localisation.repository.BusRepository;
import com.example.localisation.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final TrajetRepository trajetRepository;

    public BusService(BusRepository busRepository, TrajetRepository trajetRepository) {
        this.busRepository = busRepository;
        this.trajetRepository = trajetRepository;
    }

    public List<BusDTO> getAllBuses() {
        return busRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BusDTO getBusById(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
        return convertToDTO(bus);
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public BusDTO updateBusPosition(Long id) {
        Bus bus = busRepository.findByIdWithTrajetAndLocations(id);

        if (bus.getTrajet() == null || bus.getTrajet().getLocations().isEmpty()) {
            throw new RuntimeException("No locations available for the associated trajet");
        }

        List<BusLocation> locations = bus.getTrajet().getLocations();
        Integer currentIndex = bus.getCurrentLocationIndex();

        if (currentIndex == null) {
            currentIndex = 0;
        }

        boolean goingForward = currentIndex >= 0;

        if (goingForward) {
            if (currentIndex < locations.size() - 1) {
                currentIndex++;
            } else {
                currentIndex = -locations.size() + 1; // Reverse
            }
        } else {
            if (Math.abs(currentIndex) < locations.size()) {
                currentIndex++;
            } else {
                currentIndex = 0; // Reset to start
            }
        }

        BusLocation currentLocation = locations.get(Math.abs(currentIndex));
        bus.setCurrentLatitude(currentLocation.getLatitude());
        bus.setCurrentLongitude(currentLocation.getLongitude());
        bus.setCurrentLocationIndex(currentIndex);

        busRepository.save(bus);
        return convertToDTO(bus);
    }


    @Transactional
    @Scheduled(fixedRate = 5000) // Runs every 5 seconds
    public void updateAllBusPositions() {
        List<Bus> buses = busRepository.findAll();
        for (Bus bus : buses) {
            if (bus.getTrajet() != null && !bus.getTrajet().getLocations().isEmpty()) {
                BusDTO updatedBus = updateBusPosition(bus.getId());
                System.out.println("Updated position for bus: " + updatedBus.getName() +
                        " to Latitude: " + updatedBus.getCurrentLatitude() +
                        " Longitude: " + updatedBus.getCurrentLongitude());
            }
        }
    }

    // Converts Bus entity to BusDTO
    private BusDTO convertToDTO(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setName(bus.getName());
        dto.setCurrentLatitude(bus.getCurrentLatitude());
        dto.setCurrentLongitude(bus.getCurrentLongitude());
        dto.setCurrentLocationIndex(bus.getCurrentLocationIndex());

        if (bus.getTrajet() != null) {
            TrajetDTO trajetDTO = new TrajetDTO();
            trajetDTO.setId(bus.getTrajet().getId());
            trajetDTO.setName(bus.getTrajet().getName());
            trajetDTO.setLocations(bus.getTrajet().getLocations().stream().map(location -> {
                BusLocationDTO locationDTO = new BusLocationDTO();
                locationDTO.setId(location.getId());
                locationDTO.setLatitude(location.getLatitude());
                locationDTO.setLongitude(location.getLongitude());
                return locationDTO;
            }).collect(Collectors.toList()));
            dto.setTrajet(trajetDTO);
        }

        return dto;
    }
    @Transactional
    public BusDTO assignTrajetToBus(Long busId, Long trajetId) {
        // Find the bus
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Find the trajet
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet not found"));

        // Assign the trajet to the bus
        bus.setTrajet(trajet);

        // Save the updated bus
        busRepository.save(bus);

        // Return the updated BusDTO
        return convertToDTO(bus);
    }

}

