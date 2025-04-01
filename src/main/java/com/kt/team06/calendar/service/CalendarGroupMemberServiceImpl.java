package com.kt.team06.calendar.service;

import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import com.kt.team06.calendar.mapper.EntityMapper;
import com.kt.team06.calendar.repository.CalendarGroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarGroupMemberServiceImpl implements CalendarGroupMemberService {

    private final CalendarGroupMemberRepository calendarGroupMemberRepository;

    private final EntityMapper entityMapper;


    @Override
    public CalendarGroupMember createGroupMember(Long memberId, CalendarGroup group) {
        return calendarGroupMemberRepository.save(
                entityMapper.toCalendarGroupMember(memberId, group)
        );
    }

    @Override
    public void deleteGroupMember(List<CalendarGroupMember> calendarGroupMembers) {
        calendarGroupMemberRepository.deleteAll(calendarGroupMembers);
    }

    @Override
    public List<CalendarGroupMember> getGroupMembersByMember(Long memberId) {
        return  calendarGroupMemberRepository.findAllByMemberId(memberId);
    }

    @Override
    public Optional<CalendarGroupMember> getGroupMemberByGroupAndMember(CalendarGroup group, Long targetMemberId) {
        return calendarGroupMemberRepository.findByCalendarGroupAndMemberId(group, targetMemberId);
    }


}
