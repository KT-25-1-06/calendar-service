package com.kt.team06.calendar.dto.event;

public record CalendarIcsCreatedEvent(
        Long calendarId,
        String subscriptionUrl
) {}