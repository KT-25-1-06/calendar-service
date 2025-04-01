package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarSubscriptionRepository extends JpaRepository<CalendarSubscription, Long> {

    void deleteAllByCalendarIn(List<Calendar> calendars);
}
