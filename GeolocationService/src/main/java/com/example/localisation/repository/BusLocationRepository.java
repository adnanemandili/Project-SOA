package com.example.localisation.repository;

import com.example.localisation.model.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {

    // Find locations by bus ID
    List<BusLocation> findByBus_Id(Long busId);

    // Find locations by trajet ID
    List<BusLocation> findByTrajet_Id(Long trajetId);
}
