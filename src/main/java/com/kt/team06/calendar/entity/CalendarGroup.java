package com.kt.team06.calendar.entity;

import com.kt.team06.calendar.dto.request.group.CalendarGroupUpdateRequest;
import com.kt.team06.calendar.entity.enums.CalendarGroupType;
import com.kt.team06.calendar.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CalendarGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private CalendarGroupType type;

    @OneToMany(mappedBy = "calendarGroup")
    @Builder.Default
    private List<Calendar> calendars = new ArrayList<>();

    @OneToMany(mappedBy = "calendarGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<CalendarGroupMember> members = new ArrayList<>();

    public void updateCalendar(CalendarGroupUpdateRequest request) {
        this.name = request.name();
    }

    public void addMember(CalendarGroupMember member) {
        members.add(member);
    }

    public void removeMember(CalendarGroupMember member) {
        members.remove(member);
    }

    public void removeAllMembers() {
        members.clear();
    }

    public void addCalendar(Calendar calendar) {
        calendars.add(calendar);
    }

    public void removeCalendar(Calendar calendar) {
        calendars.remove(calendar);
    }

}
