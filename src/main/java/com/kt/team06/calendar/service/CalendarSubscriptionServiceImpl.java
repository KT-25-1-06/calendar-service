package com.kt.team06.calendar.service;

import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.repository.CalendarSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarSubscriptionServiceImpl implements CalendarSubscriptionService {

    private final CalendarSubscriptionRepository calendarSubscriptionRepository;

    @Override
    public void deleteAllByCalendar(List<Calendar> calendars) {

        // TODO: 캘린더 구독과 관련된 ICS 파일 삭제 PUB

        calendarSubscriptionRepository.deleteAllByCalendarIn(calendars);
    }
}
