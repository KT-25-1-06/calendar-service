package com.kt.team06.calendar.dto.response.group;

import java.util.List;

public record CalendarGroupListResponse(
        List<CalendarGroupSummaryResponse> calendarGroupList
) {

    public static CalendarGroupListResponse of(List<CalendarGroupSummaryResponse> calendarGroupSummaryResponses) {
        return new CalendarGroupListResponse(calendarGroupSummaryResponses);
    }
}
