package com.kt.team06.calendar.service.kafka;

import com.kt.team06.calendar.dto.pub.CalendarIcsCreateMessage;
import com.kt.team06.calendar.dto.pub.CalendarIcsDeleteMessage;
import com.kt.team06.calendar.entity.Calendar;
import com.kt.team06.calendar.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarIcsProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.calendar-ics-create}")
    private String createTopic;

    @Value("${kafka.topic.calendar-ics-delete}")
    private String deleteTopic;

    public void send(Calendar calendar, List<Schedule> schedules) {
        CalendarIcsCreateMessage message = CalendarIcsCreateMessage.of(calendar, schedules);
        kafkaTemplate.send(createTopic, String.valueOf(calendar.getId()), message);
    }

    public void sendDelete(Long calendarId) {
        CalendarIcsDeleteMessage message = new CalendarIcsDeleteMessage(calendarId);
        kafkaTemplate.send(deleteTopic, String.valueOf(calendarId), message);
    }
}
