package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.group.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.dto.response.group.CalendarGroupDetailResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupIdResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupListResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupMemberIdResponse;

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
