package com.kt.team06.calendar.dto.response.group;

import com.kt.team06.calendar.dto.response.calendar.CalendarSummaryResponse;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;

import java.util.List;

public record CalendarGroupSummaryResponse(
        Long calendarGroupId, String groupName, CalendarGroupType groupType, List<CalendarSummaryResponse> calendars
) {
    public static CalendarGroupSummaryResponse of(
            CalendarGroup calendarGroup, List<CalendarSummaryResponse> calendarGroupSummaryResponses
    ) {
        return new CalendarGroupSummaryResponse(
                calendarGroup.getId(),
                calendarGroup.getName(),
                calendarGroup.getType(),
                calendarGroupSummaryResponses
        );
    }
}
