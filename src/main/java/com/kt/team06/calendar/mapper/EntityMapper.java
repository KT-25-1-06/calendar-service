package com.kt.team06.calendar.mapper;

import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.schedule.ScheduleCreateRequest;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import com.kt.team06.calendar.entity.Schedule;
import com.kt.team06.calendar.entity.enums.ScheduleStatus;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

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

    public Calendar toCalendar(CalendarGroup calendarGroup, String name) {
        return Calendar.builder()
                .calendarGroup(calendarGroup)
                .name(name)
                .build();
    }

    public Schedule toSchedule(Long memberId, ScheduleCreateRequest request, Calendar calendar) {
        return Schedule.builder()
                .title(request.title())
                .description(request.description())
                .startAt(request.startAt())
                .endAt(request.endAt())
                .location(request.location())
                .repeatType(request.repeatType())
                .calendar(calendar)
                .writerId(memberId)
                .status(ScheduleStatus.UPCOMING)
                .build();
    }
}
