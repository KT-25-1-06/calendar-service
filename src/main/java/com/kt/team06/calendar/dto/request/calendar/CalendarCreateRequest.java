package com.kt.team06.calendar.dto.request.calendar;

public record CalendarCreateRequest(
    Long calendarGroupId, String name
) {
    public static CalendarCreateRequest of(Long calendarGroupId, String name) {
        return new CalendarCreateRequest(calendarGroupId, name);
    }
}
