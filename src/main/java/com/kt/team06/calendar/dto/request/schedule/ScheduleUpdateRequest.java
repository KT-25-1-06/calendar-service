package com.kt.team06.calendar.dto.request.schedule;

import java.time.LocalDateTime;

public record ScheduleUpdateRequest(
        String title, String description,
        LocalDateTime startAt, LocalDateTime endAt,
        String location
) {
}
