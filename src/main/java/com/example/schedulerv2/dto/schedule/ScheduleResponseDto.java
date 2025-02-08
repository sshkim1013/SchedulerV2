package com.example.schedulerv2.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;
}
