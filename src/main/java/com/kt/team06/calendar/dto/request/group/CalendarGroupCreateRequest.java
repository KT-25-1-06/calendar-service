package com.kt.team06.calendar.dto.request.group;

import com.kt.team06.calendar.entity.enums.CalendarGroupType;

public record CalendarGroupCreateRequest(
        String name, CalendarGroupType type
) {

    public static CalendarGroupCreateRequest of(String name, CalendarGroupType type) {
        return new CalendarGroupCreateRequest(name, type);
    }
}
