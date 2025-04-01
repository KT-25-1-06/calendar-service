package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.schedule.ScheduleCreateRequest;
import com.kt.team06.calendar.dto.request.schedule.ScheduleUpdateRequest;
import com.kt.team06.calendar.dto.response.schedule.ScheduleDetailResponse;
import com.kt.team06.calendar.dto.response.schedule.ScheduleIdResponse;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleIdResponse createSchedule(String memberId, Long calendarId, ScheduleCreateRequest request);
    ScheduleIdResponse updateSchedule(String memberId, Long scheduleId, ScheduleUpdateRequest request);
    ScheduleIdResponse deleteSchedule(String memberId, Long scheduleId);
    void deleteAllByCalendar(List<Calendar> calendars);
    List<ScheduleDetailResponse> findByCalendar(Calendar calendar);
    List<Schedule> findAllByCalendar(Calendar calendar);
}
