package com.kt.team06.calendar.dto.request;

import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;

public record CalendarGroupCreateRequest(
        String name, CalendarGroupType type
) {
}
