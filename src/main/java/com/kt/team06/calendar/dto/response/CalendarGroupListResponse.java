package com.kt.team06.calendar.dto.response;

import com.kt.team06.calendar.entity.CalendarGroup;

import java.util.List;

public record CalendarGroupListResponse(
        List<CalendarGroupSummaryResponse> calendarGroupList
) {

    public static CalendarGroupListResponse of(List<CalendarGroupSummaryResponse> calendarGroupSummaryResponses) {
        return new CalendarGroupListResponse(calendarGroupSummaryResponses);
    }
}
