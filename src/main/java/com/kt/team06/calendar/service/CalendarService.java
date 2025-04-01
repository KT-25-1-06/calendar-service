package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.calendar.CalendarCreateRequest;
import com.kt.team06.calendar.dto.request.calendar.CalendarUpdateRequest;
import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarIdResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarSubscriptionResponse;
import com.kt.team06.calendar.entity.CalendarGroup;

public interface CalendarService {

    CalendarIdResponse createCalendar(String memberId, CalendarCreateRequest request);
    CalendarIdResponse updateCalendarName(String memberId, Long calendarId, CalendarUpdateRequest request);
    CalendarIdResponse deleteCalendar(String memberId, Long calendarId);
    void deleteAllByGroup(CalendarGroup calendarGroup);
    CalendarDetailResponse getCalendarInfo(String memberId, Long calendarId);
    CalendarSubscriptionResponse createCalendarSubscription(String memberId, Long calendarId);
    CalendarSubscriptionResponse deleteCalendarSubscription(String memberId, Long calendarId);
}
