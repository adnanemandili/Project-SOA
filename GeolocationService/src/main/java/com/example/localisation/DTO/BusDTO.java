package com.example.localisation.DTO;

public class BusDTO {
    private Long id;
    private String name;
    private TrajetDTO trajet;
    private Double currentLatitude;
    private Double currentLongitude;
    private Integer currentLocationIndex; // Added to track the current location index

    // Getters and Setters
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

    public TrajetDTO getTrajet() {
        return trajet;
    }

    public void setTrajet(TrajetDTO trajet) {
        this.trajet = trajet;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public Integer getCurrentLocationIndex() {
        return currentLocationIndex;
    }

    public void setCurrentLocationIndex(Integer currentLocationIndex) {
        this.currentLocationIndex = currentLocationIndex;
    }
}
