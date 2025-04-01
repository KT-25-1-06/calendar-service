package com.kt.team06.calendar.controller;

import com.kt.team06.calendar.dto.request.calendar.CalendarCreateRequest;
import com.kt.team06.calendar.dto.request.calendar.CalendarUpdateRequest;
import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarIdResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarSubscriptionResponse;
import com.kt.team06.calendar.global.ApiResponse;
import com.kt.team06.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<ApiResponse<CalendarIdResponse>> createCalendar(
        @RequestHeader String memberId,
        @RequestBody CalendarCreateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(calendarService.createCalendar(memberId, request)));
    }

    @PutMapping("/{calendarId}")
    public ResponseEntity<ApiResponse<CalendarIdResponse>> updateCalendar(
        @RequestHeader String memberId,
        @PathVariable Long calendarId,
        @RequestBody CalendarUpdateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarService.updateCalendarName(memberId, calendarId, request)
        ));
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<ApiResponse<CalendarIdResponse>> deleteCalendar(
        @RequestHeader String memberId,
        @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(ApiResponse.success(calendarService.deleteCalendar(memberId, calendarId)));
    }

    @GetMapping("/{calendarId}")
    public ResponseEntity<ApiResponse<CalendarDetailResponse>> getCalendarInfo(
        @RequestHeader String memberId,
        @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(ApiResponse.success(calendarService.getCalendarInfo(memberId, calendarId)));
    }

    @PostMapping("/{calendarId}/subscription")
    public ResponseEntity<ApiResponse<CalendarSubscriptionResponse>> createCalendarSubscription(
            @RequestHeader String memberId,
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarService.createCalendarSubscription(memberId, calendarId)
        ));
    }

    @DeleteMapping("/{calendarId}/subscription")
    public ResponseEntity<ApiResponse<CalendarSubscriptionResponse>> deleteCalendarSubscription(
            @RequestHeader String memberId,
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarService.deleteCalendarSubscription(memberId, calendarId)
        ));
    }

    // TODO: 캘린더 즐겨찾기 추가/삭제 API
}
