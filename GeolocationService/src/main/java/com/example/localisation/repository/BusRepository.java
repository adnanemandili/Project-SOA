package com.example.localisation.repository;

import com.example.localisation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {


    @Query("SELECT b FROM Bus b JOIN FETCH b.trajet t JOIN FETCH t.locations WHERE b.id = :id")
    Bus findByIdWithTrajetAndLocations(@Param("id") Long id);
}
