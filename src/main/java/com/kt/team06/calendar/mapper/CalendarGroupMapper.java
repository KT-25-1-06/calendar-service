package com.kt.team06.calendar.mapper;

import com.kt.team06.calendar.dto.request.CalendarGroupCreateRequest;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import org.springframework.stereotype.Component;

@Component
public class CalendarGroupMapper {

    public CalendarGroup toCalendarGroup(Long memberId, CalendarGroupCreateRequest request) {
        return CalendarGroup.builder()
                .ownerId(memberId)
                .name(request.name())
                .type(request.type())
                .build();
    }

    public CalendarGroupMember toCalendarGroupMember(Long memberId, CalendarGroup calendarGroup) {
        return CalendarGroupMember.builder()
                .calendarGroup(calendarGroup)
                .memberId(memberId)
                .build();
    }
}
