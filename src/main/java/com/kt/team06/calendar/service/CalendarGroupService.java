package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.group.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.dto.response.group.CalendarGroupDetailResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupIdResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupListResponse;
import com.kt.team06.calendar.dto.response.group.CalendarGroupMemberIdResponse;

public interface CalendarGroupService {

    CalendarGroupIdResponse createCalendarGroup(String memberId, CalendarGroupCreateRequest request);
    CalendarGroupIdResponse updateCalendarGroupName(
            String memberId, Long calendarGroupId, CalendarGroupUpdateRequest request
    );
    CalendarGroupIdResponse deleteCalendarGroup(String memberId, Long calendarGroupId);
    CalendarGroupMemberIdResponse addMemberToCalendarGroup(
            String memberId, Long calendarGroupId, String email
    );
    void removeMemberFromCalendarGroup(
            String memberId, Long calendarGroupId, String targetMemberId
    );
    void removeMembersFromCalendarGroup(String memberId, Long calendarGroupId);
    CalendarGroupDetailResponse getCalendarGroupInfo(String memberId, Long calendarGroupId);
    CalendarGroupListResponse getMyCalendarGroupsInfo(String memberId);

}
