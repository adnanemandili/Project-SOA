package com.example.localisation.controller;

import com.example.localisation.model.BusLocation;
import com.example.localisation.service.BusLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geolocation-service/bus-locations")
public class BusLocationController {

    private final BusLocationService busLocationService;

    public BusLocationController(BusLocationService busLocationService) {
        this.busLocationService = busLocationService;
    }

    // Get all bus locations
    @GetMapping
    public ResponseEntity<List<BusLocation>> getAllBusLocations() {
        List<BusLocation> locations = busLocationService.getAllBusLocations();
        return ResponseEntity.ok(locations);
    }

    // Get bus locations by busId
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusLocation>> getLocationsByBusId(@PathVariable Long busId) {
        List<BusLocation> locations = busLocationService.getLocationsByBusId(busId);
        return ResponseEntity.ok(locations);
    }

    // Get bus locations by trajetId
    @GetMapping("/trajet/{trajetId}")
    public ResponseEntity<List<BusLocation>> getLocationsByTrajetId(@PathVariable Long trajetId) {
        List<BusLocation> locations = busLocationService.getLocationsByTrajetId(trajetId);
        return ResponseEntity.ok(locations);
    }

    // Add a new bus location
    @PostMapping
    public ResponseEntity<BusLocation> saveBusLocation(@RequestBody BusLocation busLocation) {
        BusLocation savedLocation = busLocationService.saveBusLocation(busLocation);
        return ResponseEntity.ok(savedLocation);
    }
}
