package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
