package com.kt.team06.calendar.entity;

import com.kt.team06.calendar.dto.request.calendar.CalendarUpdateRequest;
import com.kt.team06.calendar.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private CalendarGroup calendarGroup;

    @Column(nullable = false)
    private String name;

    private String subscriptionUrl;

    private String color;

    public void updateCalendar(CalendarUpdateRequest request) {
        this.name = request.name();
    }

    public void updateCalendarSubscription(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }
}
