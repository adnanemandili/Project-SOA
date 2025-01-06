package com.example.TrajectService.Services;

import com.example.TrajectService.Entities.Traject;
import com.example.TrajectService.Reposetories.TrajectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajectService {

    @Autowired
    private TrajectRepository trajectRepository;

    
    public List<Traject> findAllTrajects() {
        return trajectRepository.findAll();
    }

    
    public Optional<Traject> findTrajectById(Long id) {
        return trajectRepository.findById(id);
    }

    
    public Traject save(Traject traject) {
        return trajectRepository.save(traject);
    }

    
    public void delete(Long id) {
        trajectRepository.deleteById(id);
    }

    
    public List<Traject> findByStartAndEndPoint(String startPoint, String endPoint) {
        return trajectRepository.findByStartPointAndEndPoint(startPoint, endPoint);
    }
}
