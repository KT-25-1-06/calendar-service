package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.schedule.ScheduleCreateRequest;
import com.kt.team06.calendar.dto.request.schedule.ScheduleUpdateRequest;
import com.kt.team06.calendar.dto.response.schedule.ScheduleDetailResponse;
import com.kt.team06.calendar.dto.response.schedule.ScheduleIdResponse;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import com.kt.team06.calendar.entity.Schedule;
import com.kt.team06.calendar.mapper.EntityMapper;
import com.kt.team06.calendar.repository.CalendarRepository;
import com.kt.team06.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final CalendarRepository calendarRepository;

    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public ScheduleIdResponse createSchedule(
            String memberId, Long calendarId, ScheduleCreateRequest request
    ) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        Schedule newSchedule = scheduleRepository.save(
                entityMapper.toSchedule(memberId, request, calendar)
        );

        // TODO: ICS 서비스로 파일 생성 PUB

        return new ScheduleIdResponse(newSchedule.getId());
    }

    @Override
    @Transactional
    public ScheduleIdResponse updateSchedule(
            String memberId, Long scheduleId, ScheduleUpdateRequest request
    ) {

        Schedule schedule = scheduleRepository.getSchedule(scheduleId);
        validateScheduleAccess(memberId, schedule);

        schedule.updateSchedule(request);

        // TODO: ICS 서비스로 파일 수정 PUB

        return new ScheduleIdResponse(schedule.getId());
    }

    @Override
    @Transactional
    public ScheduleIdResponse deleteSchedule(String memberId, Long scheduleId) {

        Schedule schedule = scheduleRepository.getSchedule(scheduleId);

        // TODO: ICS 서비스로 파일 삭제 PUB

        scheduleRepository.delete(schedule);

        return new ScheduleIdResponse(schedule.getId());
    }

    @Override
    public void deleteAllByCalendar(List<Calendar> calendars) {

        List<Schedule> schedules = scheduleRepository.findAllByCalendarIn(calendars);

        // TODO: 일정과 관련된 ICS 파일 삭제 PUB

        scheduleRepository.deleteAll(schedules);
    }

    @Override
    public List<ScheduleDetailResponse> findByCalendar(Calendar calendar) {
        List<Schedule> schedules = scheduleRepository.findAllByCalendarIn(List.of(calendar));
        return schedules.stream().map(ScheduleDetailResponse::of).toList();
    }

    @Override
    public List<Schedule> findAllByCalendar(Calendar calendar) {
        return scheduleRepository.findAllByCalendar(calendar);
    }

    public void validateCalendarAccess(String memberId, CalendarGroup calendarGroup) {
        List<CalendarGroupMember> members = calendarGroup.getMembers();
        if (!members.stream().map(CalendarGroupMember::getMemberId).toList().contains(memberId)) {
            throw new AccessDeniedException("해당 캘린더 수정/삭제/조회 권한이 없습니다.");
        }
    }

    public void validateScheduleAccess(String memberId, Schedule schedule) {
        if (!schedule.getWriterId().equals(memberId)) {
            throw new AccessDeniedException("해당 일정의 수정/삭제 권한이 없습니다.");
        }
    }
}
