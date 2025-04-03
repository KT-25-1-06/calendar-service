package com.kt.team06.calendar.dto.request.calendar;

public record CalendarCreateRequest(
    Long calendarGroupId, String name, String color
) {
    public static CalendarCreateRequest of(Long calendarGroupId, String name, String color) {
        return new CalendarCreateRequest(calendarGroupId, name, color);
    }
}
