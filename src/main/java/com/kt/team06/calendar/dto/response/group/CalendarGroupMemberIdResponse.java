package com.kt.team06.calendar.dto.response.group;

public record CalendarGroupMemberIdResponse(
        Long calendarGroupMemberId
) {

    public static CalendarGroupMemberIdResponse of(Long calendarGroupMemberId) {
        return new CalendarGroupMemberIdResponse(calendarGroupMemberId);
    }
}
