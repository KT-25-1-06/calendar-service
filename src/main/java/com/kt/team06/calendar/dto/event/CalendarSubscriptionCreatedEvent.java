package com.kt.team06.calendar.dto.event;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public record CalendarSubscriptionCreatedEvent(
        Long calendarId,
        String calendarName,
        List<ScheduleEvent> schedules
) {

    public static CalendarSubscriptionCreatedEvent of(Calendar calendar, List<ScheduleEvent> schedules) {
        return new CalendarSubscriptionCreatedEvent(
                calendar.getId(),
                calendar.getName(),
                schedules
        );
    }

    public record ScheduleEvent(
            Long scheduleId,
            String title,
            String description,
            String location,
            LocalDateTime startAt,
            LocalDateTime endAt
    ) {
        public static ScheduleEvent of(Schedule schedule) {
            return new ScheduleEvent(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getDescription(),
                    schedule.getLocation(),
                    schedule.getStartAt(),
                    schedule.getEndAt()
            );
        }
    }
}
