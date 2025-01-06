package com.example.TrajectService.Repositories;

import com.example.TrajectService.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTrajectIdAndDayOfWeek(Long trajectId, String dayOfWeek);
}
