package com.kt.team06.calendar.dto.event;

public record MemberSignedUpEvent(
        String memberId,
        String email,
        String name
) {}