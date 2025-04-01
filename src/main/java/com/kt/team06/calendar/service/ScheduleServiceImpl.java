package com.kt.team06.calendar.service;

import com.kt.team06.calendar.dto.response.schedule.ScheduleDetailResponse;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;
import com.kt.team06.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

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
}
