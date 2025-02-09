package com.example.schedulerv2.service;

import com.example.schedulerv2.dto.schedule.ScheduleRequestDto;
import com.example.schedulerv2.dto.schedule.ScheduleResponseDto;
import com.example.schedulerv2.entity.Schedule;
import com.example.schedulerv2.entity.User;
import com.example.schedulerv2.repository.ScheduleRepository;
import com.example.schedulerv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        User findUser = userRepository.findUserByNameOrElseThrow(dto.getUserName());
        Schedule schedule = new Schedule(findUser, dto.getTitle(), dto.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Transactional
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> findSchedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> dtoList = new ArrayList<>();

        for (Schedule findSchedule : findSchedules) {
            dtoList.add(new ScheduleResponseDto(findSchedule));
        }

        return dtoList;
    }

    @Transactional
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule);

    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        findSchedule.update(dto.getTitle(), dto.getContent());

        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        scheduleRepository.deleteById(findSchedule.getId());
    }
}
