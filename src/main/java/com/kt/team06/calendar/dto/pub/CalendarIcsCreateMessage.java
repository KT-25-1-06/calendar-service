package com.kt.team06.calendar.dto.pub;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;

import java.util.List;

public record CalendarIcsCreateMessage(
        Long calendarId,
        String calendarName,
        List<ScheduleIcsData> schedules
) {
    public static CalendarIcsCreateMessage of(Calendar calendar, List<Schedule> schedules) {
        List<ScheduleIcsData> scheduleData = schedules.stream()
                .map(s -> new ScheduleIcsData(
                        s.getId(),
                        s.getTitle(),
                        s.getDescription(),
                        s.getStartAt(),
                        s.getEndAt(),
                        s.getLocation(),
                        s.getWriterId()))
                .toList();

        return new CalendarIcsCreateMessage(calendar.getId(), calendar.getName(), scheduleData);
    }
}