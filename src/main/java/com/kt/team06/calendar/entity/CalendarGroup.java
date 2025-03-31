package com.kt.team06.calendar.entity;

import com.kt.team06.calendar.entity.enums.CalendarGroupType;
import com.kt.team06.calendar.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CalendarGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private CalendarGroupType type;

    @OneToMany(mappedBy = "calendar_group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "calendar_group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalendarGroupMember> members;

}
