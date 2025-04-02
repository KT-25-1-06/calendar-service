package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.FavoriteCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCalendarRepository extends JpaRepository<FavoriteCalendar, Long> {

    void deleteAllByCalendarIn(List<Calendar> calendars) ;
    boolean existsByCalendarAndMemberId(Calendar calendar, String memberId);
}
