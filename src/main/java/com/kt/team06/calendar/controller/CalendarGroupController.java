package com.kt.team06.calendar.controller;

import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.group.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.dto.response.group.CalendarGroupDetailResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupIdResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupListResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupMemberIdResponse;
import com.kt.team06.calendar.global.ApiResponse;
import com.kt.team06.calendar.service.CalendarGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class CalendarGroupController {

    private final CalendarGroupService calendarGroupService;

    @PostMapping
    public ResponseEntity<ApiResponse<CalendarGroupIdResponse>> createCalendarGroup(
        @RequestHeader Long memberId,
        @RequestBody CalendarGroupCreateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(calendarGroupService.createCalendarGroup(memberId, request)));
    }

    @PutMapping("/{calendarGroupId}")
    public ResponseEntity<ApiResponse<CalendarGroupIdResponse>> updateCalendarGroupName(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId,
        @RequestBody CalendarGroupUpdateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarGroupService.updateCalendarGroupName(memberId, calendarGroupId, request)
        ));
    }

    @DeleteMapping("/{calendarGroupId}")
    public ResponseEntity<ApiResponse<CalendarGroupIdResponse>> deleteCalendarGroup(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarGroupService.deleteCalendarGroup(memberId, calendarGroupId)
        ));
    }

    @PostMapping("/{calendarGroupId}/members/{email}")
    public ResponseEntity<ApiResponse<CalendarGroupMemberIdResponse>> addMemberToCalendarGroup(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId,
        @PathVariable String email
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarGroupService.addMemberToCalendarGroup(memberId, calendarGroupId, email)
        ));
    }

    @DeleteMapping("/{calendarGroupId}/members/{targetMemberId}")
    public ResponseEntity<ApiResponse<Void>> removeMemberFromCalendarGroup(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId,
        @PathVariable Long targetMemberId
    ) {
        calendarGroupService.removeMemberFromCalendarGroup(memberId, calendarGroupId, targetMemberId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{calendarGroupId}/members")
    public ResponseEntity<ApiResponse<Void>> removeMembersFromCalendarGroup(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId
    ) {
        calendarGroupService.removeMembersFromCalendarGroup(memberId, calendarGroupId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{calendarGroupId}")
    public ResponseEntity<ApiResponse<CalendarGroupDetailResponse>> getCalendarGroupInfo(
        @RequestHeader Long memberId,
        @PathVariable Long calendarGroupId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarGroupService.getCalendarGroupInfo(memberId, calendarGroupId)
        ));
    }

    @GetMapping("/mine")
    public ResponseEntity<ApiResponse<CalendarGroupListResponse>> getMyCalendarGroupsInfo(
        @RequestHeader Long memberId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                calendarGroupService.getMyCalendarGroupsInfo(memberId)
        ));
    }

}
