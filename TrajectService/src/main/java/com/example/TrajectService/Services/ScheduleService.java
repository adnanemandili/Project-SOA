package com.example.TrajectService.Services;

import com.example.TrajectService.Entities.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TrajectService.Repositories.ScheduleRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> findScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> findByTrajectAndDayOfWeek(Long trajectId, String dayOfWeek) {
        return scheduleRepository.findByTrajectIdAndDayOfWeek(trajectId, dayOfWeek);
    }
}
