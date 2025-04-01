package com.kt.team06.calendar.service.kafka;

import com.kt.team06.calendar.dto.sub.CalendarIcsCreatedMessage;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CalendarIcsConsumer {

    private final CalendarRepository calendarRepository;

    @KafkaListener(topics = "${kafka.topic.calendar-ics-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(CalendarIcsCreatedMessage message) {
        Calendar calendar = calendarRepository.findById(message.calendarId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 캘린더입니다."));
        calendar.updateCalendarSubscription(message.subscriptionUrl());
        calendarRepository.save(calendar);
    }
}
