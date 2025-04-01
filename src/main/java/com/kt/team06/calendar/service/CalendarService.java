package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.calendar.CalendarCreateRequest;
import com.kt.team06.calendar.dto.request.calendar.CalendarUpdateRequest;
import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarIdResponse;
import com.kt.team06.calendar.entity.CalendarGroup;

public interface CalendarService {

    CalendarIdResponse createCalendar(Long memberId, CalendarCreateRequest request);
    CalendarIdResponse updateCalendarName(Long memberId, Long calendarId, CalendarUpdateRequest request);
    CalendarIdResponse deleteCalendar(Long memberId, Long calendarId);
    void deleteAllByGroup(CalendarGroup calendarGroup);
    CalendarDetailResponse getCalendarInfo(Long memberId, Long calendarId);
}
