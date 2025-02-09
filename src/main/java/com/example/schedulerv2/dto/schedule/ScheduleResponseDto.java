package com.example.schedulerv2.dto.schedule;

import com.example.schedulerv2.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userName = schedule.getUser().getName();  // 클라이언트 화면에서는 유저명을 확인 가능하도록 출력.
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
    }
}
