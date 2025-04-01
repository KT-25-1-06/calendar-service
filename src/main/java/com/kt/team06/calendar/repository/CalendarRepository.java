package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    default Calendar getCalendar(Long calendarId) {
        return findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캘린더 id입니다."));
    }
}
