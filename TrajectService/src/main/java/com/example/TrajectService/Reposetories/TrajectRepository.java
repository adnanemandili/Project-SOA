package com.example.TrajectService.Reposetories;

import com.example.TrajectService.Entities.Traject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TrajectRepository extends JpaRepository<Traject, Long> {
    List<Traject> findByStartPointAndEndPoint(String startPoint, String endPoint);
}