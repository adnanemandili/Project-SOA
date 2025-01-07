package com.example.localisation.controller;

import com.example.localisation.DTO.BusDTO;
import com.example.localisation.model.Bus;
import com.example.localisation.service.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/geolocation-service/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    /**
     * Get all buses with their details.
     */
    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    /**
     * Get a specific bus by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }

    /**
     * Create a new bus.
     */
    @PostMapping
    public ResponseEntity<Bus> saveBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.saveBus(bus));
    }

    /**
     * Delete a bus by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update the position of a bus.
     * This endpoint can be triggered manually to update the position of a specific bus.
     */
    @PutMapping("/{id}/update-position")
    public ResponseEntity<BusDTO> updateBusPosition(@PathVariable Long id) {
        BusDTO updatedBus = busService.updateBusPosition(id);
        return ResponseEntity.ok(updatedBus);
    }
    @PutMapping("/{busId}/assign-trajet/{trajetId}")
    public ResponseEntity<BusDTO> assignTrajetToBus(@PathVariable Long busId, @PathVariable Long trajetId) {
        BusDTO updatedBus = busService.assignTrajetToBus(busId, trajetId);
        return ResponseEntity.ok(updatedBus);
    }
}
