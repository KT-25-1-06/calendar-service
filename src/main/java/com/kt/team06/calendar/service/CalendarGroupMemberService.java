package com.kt.team06.calendar.service;

import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;

import java.util.List;
import java.util.Optional;

public interface CalendarGroupMemberService {

    CalendarGroupMember createGroupMember(Long memberId, CalendarGroup group);
    void deleteGroupMember(List<CalendarGroupMember> calendarGroupMembers);
    List<CalendarGroupMember> getGroupMembersByMember(Long memberId);
    Optional<CalendarGroupMember> getGroupMemberByGroupAndMember(CalendarGroup group, Long targetMemberId);
}
