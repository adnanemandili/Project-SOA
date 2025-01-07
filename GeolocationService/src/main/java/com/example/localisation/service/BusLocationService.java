package com.example.localisation.service;

import com.example.localisation.model.BusLocation;
import com.example.localisation.repository.BusLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusLocationService {

    private final BusLocationRepository busLocationRepository;

    public BusLocationService(BusLocationRepository busLocationRepository) {
        this.busLocationRepository = busLocationRepository;
    }

    public List<BusLocation> getAllBusLocations() {
        return busLocationRepository.findAll();
    }

    public List<BusLocation> getLocationsByBusId(Long busId) {
        return busLocationRepository.findByBus_Id(busId);
    }

    public List<BusLocation> getLocationsByTrajetId(Long trajetId) {
        return busLocationRepository.findByTrajet_Id(trajetId);
    }

    public BusLocation saveBusLocation(BusLocation busLocation) {
        return busLocationRepository.save(busLocation);
    }
}
