package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.response.schedule.ScheduleDetailResponse;
import com.kt.team06.calendar.entity.Calendar;

import java.util.List;

public interface ScheduleService {

    void deleteAllByCalendar(List<Calendar> calendars);
    List<ScheduleDetailResponse> findByCalendar(Calendar calendar);
}
