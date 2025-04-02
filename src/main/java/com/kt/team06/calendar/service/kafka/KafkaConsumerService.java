package com.kt.team06.calendar.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.team06.calendar.dto.event.CalendarIcsCreatedEvent;
import com.kt.team06.calendar.dto.event.MemberSignedUpEvent;
import com.kt.team06.calendar.dto.request.group.CalendarGroupCreateRequest;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;
import com.kt.team06.calendar.service.CalendarGroupService;
import com.kt.team06.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final CalendarGroupService calendarGroupService;
    private final CalendarService calendarService;

    @KafkaListener(topics = "${kafka.topic.member-signed-up}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMemberSignedUp(String rawMessage) {
        try {
            MemberSignedUpEvent event = objectMapper.readValue(rawMessage, MemberSignedUpEvent.class);
            log.info("✅ Kafka Received MemberSignedUpEvent: {}", event);
            calendarGroupService.createCalendarGroup(
                    event.memberId(), CalendarGroupCreateRequest.of("개인 캘린더", CalendarGroupType.INDIVIDUAL)
            );
        } catch (Exception e) {
            log.warn("⚠️ Kafka 파싱 실패: {}", rawMessage);
        }
    }


    @KafkaListener(topics = "${kafka.topic.calendar-ics-created}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeCalendarIcsCreated(String rawMessage) {
        try {
            CalendarIcsCreatedEvent event = objectMapper.readValue(rawMessage, CalendarIcsCreatedEvent.class);
            log.info("✅ Kafka Received CalendarIcsCreatedEvent: {}", event);

            calendarService.updateCalendarSubscriptionUrl(event.calendarId(), event.subscriptionUrl());

        } catch (Exception e) {
            log.warn("⚠️ Kafka 파싱 실패 (CalendarIcsCreatedEvent): {}", rawMessage, e);
        }
    }

    // TODO: 구독 ICS 파일 실패 보상 트랜잭션 추가해도 됨.
}
