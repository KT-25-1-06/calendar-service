package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByCalendarIn(List<Calendar> calendars);
}
