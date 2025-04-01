package com.kt.team06.calendar.service;

import com.kt.team06.calendar.entity.Calendar;

import java.util.List;

public interface CalendarSubscriptionService {

    void deleteAllByCalendar(List<Calendar> calendars);
}
