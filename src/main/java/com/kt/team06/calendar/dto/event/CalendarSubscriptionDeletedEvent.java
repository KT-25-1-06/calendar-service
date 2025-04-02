package com.kt.team06.calendar.dto.event;

public record CalendarSubscriptionDeletedEvent(
        Long calendarId
) {

    public static CalendarSubscriptionDeletedEvent of(Long calendarId) {
        return new CalendarSubscriptionDeletedEvent(calendarId);
    }
}
