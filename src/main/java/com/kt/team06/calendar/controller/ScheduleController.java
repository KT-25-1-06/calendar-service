package com.kt.team06.calendar.controller;

import com.kt.team06.calendar.dto.request.schedule.ScheduleCreateRequest;
import com.kt.team06.calendar.dto.request.schedule.ScheduleUpdateRequest;
import com.kt.team06.calendar.dto.response.schedule.ScheduleIdResponse;
import com.kt.team06.calendar.global.ApiResponse;
import com.kt.team06.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/{calendarId}/schedules")
    public ResponseEntity<ApiResponse<ScheduleIdResponse>> createSchedule(
        @RequestHeader Long memberId,
        @PathVariable Long calendarId,
        @RequestBody ScheduleCreateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.createSchedule(memberId, calendarId, request)));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleIdResponse>> updateSchedule(
        @RequestHeader Long memberId,
        @PathVariable Long scheduleId,
        @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.updateSchedule(memberId, scheduleId, request)));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleIdResponse>> deleteSchedule(
        @RequestHeader Long memberId,
        @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(ApiResponse.success(scheduleService.deleteSchedule(memberId, scheduleId)));
    }
}
