package com.kt.team06.calendar.dto.response.calendar;

import com.kt.team06.calendar.dto.response.schedule.ScheduleDetailResponse;
import com.kt.team06.calendar.entity.Calendar;

import java.util.List;

public record CalendarDetailResponse(
        Long calendarId, String name, List<ScheduleDetailResponse> schedules
) {

    public static CalendarDetailResponse of(
            Calendar calendar, List<ScheduleDetailResponse> schedules
    ) {
        return new CalendarDetailResponse(
                calendar.getId(),
                calendar.getName(),
                schedules
        );
    }
}
