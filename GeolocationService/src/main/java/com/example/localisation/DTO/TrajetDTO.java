package com.example.localisation.DTO;

import java.util.List;

public class TrajetDTO {
    private Long id;
    private String name;
    private List<BusLocationDTO> locations;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BusLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<BusLocationDTO> locations) {
        this.locations = locations;
    }
}
