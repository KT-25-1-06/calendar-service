package com.kt.team06.calendar.entity;

import com.kt.team06.calendar.dto.request.schedule.ScheduleUpdateRequest;
import com.kt.team06.calendar.entity.enums.RepeatType;
import com.kt.team06.calendar.entity.enums.ScheduleStatus;
import com.kt.team06.calendar.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Calendar calendar;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    private String location;

    @Column(nullable = false)
    private ScheduleStatus status;

    @Column(nullable = false)
    private RepeatType repeatType;

    public void updateSchedule(ScheduleUpdateRequest request) {
        this.title = request.title();
        this.description = request.description();
        this.startAt = request.startAt();
        this.endAt = request.endAt();
        this.location = request.location();
    }

}
