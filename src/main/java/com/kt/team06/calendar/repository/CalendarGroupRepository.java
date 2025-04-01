package com.kt.team06.calendar.repository;

import com.kt.team06.calendar.entity.CalendarGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarGroupRepository extends JpaRepository<CalendarGroup, Long> {

    default CalendarGroup getCalendarGroup(Long calendarGroupId) {
        return findById(calendarGroupId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캘린더그룹 id입니다."));
    }
    boolean existsByOwnerIdAndName(String ownerId, String name);
}
