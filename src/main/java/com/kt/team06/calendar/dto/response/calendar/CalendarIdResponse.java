package com.kt.team06.calendar.dto.response.calendar;

public record CalendarIdResponse(
        Long calendarId
) {

    public static CalendarIdResponse of(Long calendarId) {
        return new CalendarIdResponse(calendarId);
    }
}
