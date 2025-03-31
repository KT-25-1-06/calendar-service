package com.kt.team06.calendar.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalendarGroupType {

    TEAM("팀"),
    INDIVIDUAL("개인");

    private final String toKorean;
}
