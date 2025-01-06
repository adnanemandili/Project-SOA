package com.example.TrajectService.controllers;

import com.example.TrajectService.Entities.Traject;
import com.example.TrajectService.Services.TrajectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/traject-service/trajects")
@RequiredArgsConstructor
public class TrajectController {

    @Autowired
    private TrajectService trajectService;

    @GetMapping
    public ResponseEntity<List<Traject>> findAllTrajects() {
        return ResponseEntity.ok(trajectService.findAllTrajects());
    }

    @GetMapping("/{id}")
    public Optional<Traject> findTrajectById(@PathVariable("id") Long id) {
        return trajectService.findTrajectById(id);
    }


    @PostMapping
    public ResponseEntity<Traject> createTraject(@RequestBody Traject traject) {
        return ResponseEntity.ok(trajectService.save(traject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraject(@PathVariable Long id) {
        trajectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Traject>> findByStartAndEndPoint(
            @RequestParam("startPoint") String startPoint,
            @RequestParam("endPoint") String endPoint) {
        List<Traject> trajects = trajectService.findByStartAndEndPoint(startPoint, endPoint);
        if (trajects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trajects);
    }
}
