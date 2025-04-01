package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.request.calendar.CalendarCreateRequest;
import com.kt.team06.calendar.dto.request.calendar.CalendarUpdateRequest;
import com.kt.team06.calendar.dto.response.calendar.CalendarDetailResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarIdResponse;
import com.kt.team06.calendar.dto.response.calendar.CalendarSubscriptionResponse;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.entity.CalendarGroupMember;
import com.kt.team06.calendar.entity.Schedule;
import com.kt.team06.calendar.mapper.EntityMapper;
import com.kt.team06.calendar.repository.CalendarGroupRepository;
import com.kt.team06.calendar.repository.CalendarRepository;
import com.kt.team06.calendar.repository.FavoriteCalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarGroupRepository calendarGroupRepository;
    private final FavoriteCalendarRepository favoriteCalendarRepository;

    private final ScheduleService scheduleService;

    private final EntityMapper entityMapper;

//    private final CalendarIcsProducer calendarIcsProducer;


    @Override
    @Transactional
    public CalendarIdResponse createCalendar(String memberId, CalendarCreateRequest request) {

        CalendarGroup calendarGroup = calendarGroupRepository.getCalendarGroup(request.calendarGroupId());
        validateCalendarGroupAccess(memberId, calendarGroup);

        Calendar newCalendar = calendarRepository.save(entityMapper.toCalendar(calendarGroup, request.name()));
        calendarGroup.addCalendar(newCalendar);

        return new CalendarIdResponse(newCalendar.getId());
    }

    @Override
    @Transactional
    public CalendarIdResponse updateCalendarName(String memberId, Long calendarId, CalendarUpdateRequest request) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        calendar.updateCalendar(request);

        return CalendarIdResponse.of(calendar.getId());
    }

    @Override
    @Transactional
    public CalendarIdResponse deleteCalendar(String memberId, Long calendarId) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        scheduleService.deleteAllByCalendar(List.of(calendar));
        calendar.getCalendarGroup().removeCalendar(calendar);

        // Kafka Pub - ICS 파일 삭제 요청
//        if (calendar.getSubscriptionUrl() != null)
//            calendarIcsProducer.sendDelete(calendar.getId());

        calendarRepository.delete(calendar);

        return CalendarIdResponse.of(calendar.getId());
    }

    @Override
    public void deleteAllByGroup(CalendarGroup calendarGroup) {

        List<Calendar> calendars = calendarGroup.getCalendars();

        scheduleService.deleteAllByCalendar(calendars);
        favoriteCalendarRepository.deleteAllByCalendarIn(calendars);

        // Kafka Pub - 각 캘린더별 ICS 삭제 요청
//        for (Calendar calendar : calendars) {
//            if (calendar.getSubscriptionUrl() != null)
//                calendarIcsProducer.sendDelete(calendar.getId());
//        }

        calendarRepository.deleteAll(calendars);
    }

    @Override
    public CalendarDetailResponse getCalendarInfo(String memberId, Long calendarId) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        return CalendarDetailResponse.of(
                calendar, scheduleService.findByCalendar(calendar)
        );
    }

    @Override
    @Transactional
    public CalendarSubscriptionResponse createCalendarSubscription(String memberId, Long calendarId) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        List<Schedule> schedules = scheduleService.findAllByCalendar(calendar);

        // Kafka Pub
//        calendarIcsProducer.send(calendar, schedules);

        return CalendarSubscriptionResponse.of(calendar);
    }

    @Override
    @Transactional
    public CalendarSubscriptionResponse deleteCalendarSubscription(String memberId, Long calendarId) {

        Calendar calendar = calendarRepository.getCalendar(calendarId);
        validateCalendarAccess(memberId, calendar.getCalendarGroup());

        // Kafka Pub - ICS 파일 삭제 요청
//        calendarIcsProducer.sendDelete(calendar.getId());

        calendar.updateCalendarSubscription(null);
        return CalendarSubscriptionResponse.of(calendar);
    }

    public void validateCalendarGroupAccess(String memberId, CalendarGroup calendarGroup) {
        List<CalendarGroupMember> members = calendarGroup.getMembers();
        if (!members.stream().map(CalendarGroupMember::getMemberId).toList().contains(memberId)) {
            throw new AccessDeniedException("해당 캘린더 그룹에 캘린더 추가 권한이 없습니다.");
        }
    }

    public void validateCalendarAccess(String memberId, CalendarGroup calendarGroup) {
        List<CalendarGroupMember> members = calendarGroup.getMembers();
        if (!members.stream().map(CalendarGroupMember::getMemberId).toList().contains(memberId)) {
            throw new AccessDeniedException("해당 캘린더 수정/삭제/조회 권한이 없습니다.");
        }
    }

}
