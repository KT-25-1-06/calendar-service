package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarGroupMemberRepository extends JpaRepository<CalendarGroupMember, Long> {
    Optional<CalendarGroupMember> findByCalendarGroupAndMemberId(CalendarGroup calendarGroup, Long memberId);
    List<CalendarGroupMember> findAllByMemberId(Long memberId);
}
