package com.kt.team06.calendar.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleStatus {
    UPCOMING("예정"),
    ONGOING("진행 중"),
    COMPLETED("완료 됨"),
    CANCELED("취소됨"),
    ;

    private final String toKorean;
}
