package com.kt.team06.calendar.dto.pub;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ScheduleIcsData(
        Long scheduleId,
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String location,
        String memberId
) {}
