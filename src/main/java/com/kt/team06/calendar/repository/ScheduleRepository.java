package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule getSchedule(Long scheduleId) {
        return findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정 id입니다."));
    }

    List<Schedule> findAllByCalendarIn(List<Calendar> calendars);
}
