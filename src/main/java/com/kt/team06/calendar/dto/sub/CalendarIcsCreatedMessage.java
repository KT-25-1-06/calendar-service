package com.kt.team06.calendar.dto.sub;

import lombok.Builder;

@Builder
public record CalendarIcsCreatedMessage(
        Long calendarId,
        String subscriptionUrl
) {}