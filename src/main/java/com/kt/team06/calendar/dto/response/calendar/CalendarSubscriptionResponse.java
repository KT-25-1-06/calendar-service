package com.kt.team06.calendar.dto.response.calendar;

import com.kt.team06.calendar.entity.Calendar;

public record CalendarSubscriptionResponse(
        Long calendarId, String subscriptionUrl
) {

    public static CalendarSubscriptionResponse of(Calendar calendar) {
        return new CalendarSubscriptionResponse(
                calendar.getId(),
                calendar.getSubscriptionUrl()
        );
    }
}
