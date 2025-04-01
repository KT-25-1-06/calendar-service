package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.dto.response.CalendarGroupDetailResponse;
import com.kt.team06.calendar.dto.response.CalendarGroupIdResponse;
import com.kt.team06.calendar.dto.response.CalendarGroupListResponse;
import com.kt.team06.calendar.dto.response.CalendarGroupMemberIdResponse;

public interface CalendarGroupService {

    CalendarGroupIdResponse createCalendarGroup(Long memberId, CalendarGroupCreateRequest request);
    CalendarGroupIdResponse updateCalendarGroupName(
            Long memberId, Long calendarGroupId, CalendarGroupUpdateRequest request
    );
    CalendarGroupIdResponse deleteCalendarGroup(Long memberId, Long calendarGroupId);
    CalendarGroupMemberIdResponse addMemberToCalendarGroup(
            Long memberId, Long calendarGroupId, String email
    );
    void removeMemberFromCalendarGroup(
            Long memberId, Long calendarGroupId, Long targetMemberId
    );
    void removeMembersFromCalendarGroup(Long memberId, Long calendarGroupId);
    CalendarGroupDetailResponse getCalendarGroupInfo(Long memberId, Long calendarGroupId);
    CalendarGroupListResponse getMyCalendarGroupsInfo(Long memberId);

}
