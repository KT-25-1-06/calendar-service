package com.kt.team06.calendar.dto.response.group;

import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;

import java.util.List;

public record CalendarGroupDetailResponse(
        Long calendarGroupId, String groupName, CalendarGroupType type,
        List<CalendarDetailResponse> calendars
) {

    public static CalendarGroupDetailResponse of(
            CalendarGroup calendarGroup, List<CalendarDetailResponse> calendars
    ) {
        return new CalendarGroupDetailResponse(
                calendarGroup.getId(),
                calendarGroup.getName(),
                calendarGroup.getType(),
                calendars
        );
    }
}
