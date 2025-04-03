package com.kt.team06.calendar.dto.response.calendar;

import com.kt.team06.calendar.entity.Calendar;

public record CalendarSummaryResponse(
        Long calendarId, String name, String subscriptionUrl, String color, Boolean isFavorite
) {
    public static CalendarSummaryResponse of(Calendar calendar, Boolean isFavorite) {
        return new CalendarSummaryResponse(
                calendar.getId(), calendar.getName(), calendar.getSubscriptionUrl(), calendar.getColor(), isFavorite
        );
    }
}
