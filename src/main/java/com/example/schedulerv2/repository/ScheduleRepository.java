package com.example.schedulerv2.repository;

import com.example.schedulerv2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //특정 스케줄 존재 여부 검증 로직
    default Schedule findScheduleByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 일정이 존재하지 않습니다. 입력하신 ID = " + id));
    }



}
