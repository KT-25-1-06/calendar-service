package com.kt.team06.calendar.dto.response.schedule;

import com.kt.team06.calendar.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleDetailResponse(
        Long scheduleId, String title, String description,
        LocalDateTime startAt, LocalDateTime endAt, String location
) {

    public static ScheduleDetailResponse of(Schedule schedule) {
        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getLocation()
        );
    }
}
