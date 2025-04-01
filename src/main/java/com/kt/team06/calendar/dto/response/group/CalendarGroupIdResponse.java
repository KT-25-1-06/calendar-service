package com.kt.team06.calendar.dto.response.group;

public record CalendarGroupIdResponse(
        Long calendarGroupId
) {
    public static CalendarGroupIdResponse of(Long calendarGroupId) {
        return new CalendarGroupIdResponse(calendarGroupId);
    }
}
