package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.response.CalendarGroupDetailResponse;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.CalendarGroup;
import com.kt.team06.calendar.repository.CalendarRepository;
import com.kt.team06.calendar.repository.FavoriteCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final FavoriteCalendarRepository favoriteCalendarRepository;

    private final ScheduleService scheduleService;
    private final CalendarSubscriptionService calendarSubscriptionService;


    @Override
    public void deleteAllByGroup(CalendarGroup calendarGroup) {

        List<Calendar> calendars = calendarGroup.getCalendars();

        scheduleService.deleteAllByCalendar(calendars);
        favoriteCalendarRepository.deleteAllByCalendarIn(calendars);
        calendarSubscriptionService.deleteAllByCalendar(calendars);

        calendarRepository.deleteAll(calendars);
    }

}
