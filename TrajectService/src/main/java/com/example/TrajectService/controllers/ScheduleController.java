package com.example.TrajectService.controllers;

import com.example.TrajectService.Entities.Schedule;
import com.example.TrajectService.Services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@PreAuthorize("hasRole('client_passenger')")
@RequestMapping("api/v1/traject-service/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Schedule>> findAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAllSchedules());
    }

    @GetMapping("/{id}")
    public Optional<Schedule> findScheduleById(@PathVariable("id") Long id) {
        return scheduleService.findScheduleById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Schedule>> findByTrajectAndDayOfWeek(
            @RequestParam("trajectId") Long trajectId,
            @RequestParam("dayOfWeek") String dayOfWeek) {
        List<Schedule> schedules = scheduleService.findByTrajectAndDayOfWeek(trajectId, dayOfWeek);
        if (schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.save(schedule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
