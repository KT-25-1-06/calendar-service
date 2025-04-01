package com.kt.team06.calendar.dto.request.schedule;

import com.kt.team06.calendar.entity.enums.RepeatType;

import java.time.LocalDateTime;

public record ScheduleCreateRequest(
        String title, String description,
        LocalDateTime startAt, LocalDateTime endAt,
        String location, RepeatType repeatType
) {
}
