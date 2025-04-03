package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.calendar.CalendarCreateRequest;
import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.dto.request.group.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarSummaryResponse;
import com.kt.team06.calendar.dto.response.group.*;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;
import com.kt.team06.calendar.mapper.EntityMapper;
import com.kt.team06.calendar.repository.CalendarGroupRepository;
import com.kt.team06.calendar.repository.FavoriteCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarGroupServiceImpl implements CalendarGroupService {

    private final CalendarGroupRepository calendarGroupRepository;
    private final FavoriteCalendarRepository favoriteCalendarRepository;

    private final EntityMapper calendarGroupMapper;

    private final CalendarGroupMemberService calendarGroupMemberService;
    private final CalendarService calendarService;
    private final ScheduleService scheduleService;

    @Override
    @Transactional
    public CalendarGroupIdResponse createCalendarGroup(String memberId, CalendarGroupCreateRequest request) {

        if (calendarGroupRepository.existsByOwnerIdAndName(memberId, request.name()))
            throw new IllegalArgumentException("같은 이름의 캘린더 그룹이 존재합니다.");

        CalendarGroup group = calendarGroupRepository.save(calendarGroupMapper.toCalendarGroup(memberId, request));
        calendarGroupMemberService.createGroupMember(memberId, group);
        calendarService.createCalendar(memberId, CalendarCreateRequest.of(group.getId(), "기본", "red"));

        return CalendarGroupIdResponse.of(group.getId());
    }

    @Override
    @Transactional
    public CalendarGroupIdResponse updateCalendarGroupName(
            String memberId, Long calendarGroupId, CalendarGroupUpdateRequest request
    ) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);

        if (validateUpdate(memberId, group))
            throw new IllegalArgumentException("수정 권한이 없습니다.");

        if (calendarGroupRepository.existsByOwnerIdAndName(memberId, request.name()))
            throw new IllegalArgumentException("같은 이름의 캘린더 그룹이 존재합니다.");

        group.updateCalendar(request);
        return CalendarGroupIdResponse.of(group.getId());
    }

    @Override
    @Transactional
    public CalendarGroupIdResponse deleteCalendarGroup(String memberId, Long calendarGroupId) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);

        if (!group.getOwnerId().equals(memberId))
            throw new AccessDeniedException("삭제 권한이 없습니다.");

        if (group.getType().equals(CalendarGroupType.INDIVIDUAL))
            throw new IllegalArgumentException("개인 캘린더는 삭제할 수 없습니다.");

        calendarService.deleteAllByGroup(group);
        calendarGroupRepository.delete(group);
        return CalendarGroupIdResponse.of(group.getId());
    }

    @Override
    @Transactional
    public CalendarGroupMemberIdResponse addMemberToCalendarGroup(String memberId, Long calendarGroupId, String email) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);
        if (!group.getOwnerId().equals(memberId))
            throw new AccessDeniedException("멤버 추가 권한이 없습니다.");

        validateTeamCalendar(group);

        // TODO: 멤버 서버로 PUB(이메일을 통해 유저 찾기) -> 없다면, 예외 처리
        String targetMemberId = "test";

        if(calendarGroupMemberService.getGroupMemberByGroupAndMember(group, targetMemberId).isPresent())
                throw new IllegalArgumentException("해당 멤버가 이미 팀 캘린더에 소속되어 있습니다.");

        CalendarGroupMember newGroupMember = calendarGroupMemberService.createGroupMember(targetMemberId, group);
        group.addMember(newGroupMember);

        return CalendarGroupMemberIdResponse.of(newGroupMember.getId());
    }

    @Override
    @Transactional
    public void removeMemberFromCalendarGroup(String memberId, Long calendarGroupId, String targetMemberId) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);
        validateDeleteMember(memberId, group);
        validateTeamCalendar(group);

        CalendarGroupMember calendarGroupMember =
                calendarGroupMemberService.getGroupMemberByGroupAndMember(group, targetMemberId)
                        .orElseThrow(() -> new IllegalArgumentException("삭제하려는 팀 멤버가 존재하지 않습니다."));

        group.removeMember(calendarGroupMember);
        calendarGroupMemberService.deleteGroupMember(List.of(calendarGroupMember));
    }

    @Override
    @Transactional
    public void removeMembersFromCalendarGroup(String memberId, Long calendarGroupId) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);
        validateDeleteMember(memberId, group);
        validateTeamCalendar(group);

        List<CalendarGroupMember> groupMembers = group.getMembers();
        group.removeAllMembers();
        calendarGroupMemberService.deleteGroupMember(groupMembers);
    }

    @Override
    public CalendarGroupDetailResponse getCalendarGroupInfo(String memberId, Long calendarGroupId) {

        CalendarGroup group = calendarGroupRepository.getCalendarGroup(calendarGroupId);
        if(!group.getMembers().stream().map(CalendarGroupMember::getMemberId).toList().contains(memberId)) {
            throw new AccessDeniedException("해당 캘린더 그룹 조회 권한이 없습니다.");
        }

        return CalendarGroupDetailResponse.of(
                group,
                group.getCalendars().stream()
                        .map(calendar -> CalendarDetailResponse.of(
                                calendar,
                                scheduleService.findByCalendar(calendar)
                        ))
                        .toList()
        );
    }

    @Override
    public CalendarGroupListResponse getMyCalendarGroupsInfo(String memberId) {

        List<CalendarGroup> calendarGroups =
                calendarGroupMemberService.getGroupMembersByMember(memberId).stream()
                        .map(CalendarGroupMember::getCalendarGroup)
                        .toList();

        List<CalendarGroupSummaryResponse> responses = calendarGroups.stream()
                .map(group -> {
                    List<Calendar> calendars = group.getCalendars();
                    List<CalendarSummaryResponse> calendarSummaryResponses = calendars.stream()
                            .map(calendar -> CalendarSummaryResponse.of(
                                    calendar,
                                    favoriteCalendarRepository.existsByCalendarAndMemberId(calendar, memberId)
                            ))
                            .toList();

                    return CalendarGroupSummaryResponse.of(group, calendarSummaryResponses);
                })
                .toList();

        return CalendarGroupListResponse.of(responses);
    }

    // TODO: 회원가입 시, 개인 캘린더 그룹 생성 + 기본캘린더까지

    private boolean validateUpdate(String memberId, CalendarGroup group) {
        List<Long> groupMemberIds = group.getMembers().stream()
                .map(CalendarGroupMember::getId)
                .toList();

        return groupMemberIds.contains(memberId);
    }

    private void validateTeamCalendar(CalendarGroup group) {
        if (group.getType().equals(CalendarGroupType.INDIVIDUAL))
            throw new IllegalArgumentException("개인 캘리더는 멤버를 추가/삭제할 수 없습니다.");
    }

    private void validateDeleteMember(String memberId, CalendarGroup group) {
        if (!group.getOwnerId().equals(memberId))
            throw new AccessDeniedException("멤버 삭제 권한이 없습니다.");
    }
}
