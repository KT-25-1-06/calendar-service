package com.kt.team06.calendar.dto.response;

import com.kt.team06.calendar.entity.CalendarGroup;

import java.util.List;

public record CalendarGroupSummaryResponse(
        Long calendarGroupId, String groupName, List<CalendarSummaryResponse> calendars
) {
    public static CalendarGroupSummaryResponse of(
            CalendarGroup calendarGroup, List<CalendarSummaryResponse> calendarGroupSummaryResponses
    ) {
        return new CalendarGroupSummaryResponse(
                calendarGroup.getId(),
                calendarGroup.getName(),
                calendarGroupSummaryResponses
        );
    }
}
