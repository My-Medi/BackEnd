package com.my_medi.domain.schedule.service;


import com.my_medi.api.schedule.dto.EditScheduleDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.exception.ScheduleErrorStatus;
import com.my_medi.domain.schedule.exception.ScheduleHandler;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {

    private final ScheduleRepository scheduleRepository;
    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;

    @Override

    public Long registerScheduleToUser(Long expertId, Long userId, RegisterScheduleDto registerScheduleDto) {
        //TODO expert와 user 사이에 approved된 consultation 존재 확인
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ScheduleHandler(ScheduleErrorStatus.EXPERT_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ScheduleHandler(ScheduleErrorStatus.USER_NOT_FOUND));

        Schedule schedule = Schedule.builder()
                .expert(expert)
                .user(user)
                .title(registerScheduleDto.getTitle())
                .description(registerScheduleDto.getDescription())
                .startTime(registerScheduleDto.getStartTime())
                .endTime(registerScheduleDto.getEndTime())
                .location(registerScheduleDto.getLocation())
                .build();

        return scheduleRepository.save(schedule).getId();
    }


    @Override
    public Long editSchedule(Long expertId, Long scheduleId , EditScheduleDto editScheduleDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() ->ScheduleHandler.NOT_FOUND);

//        validateExpert(schedule, expertId);
        // 나중에 하기

         schedule.update(editScheduleDto);
        return schedule.getId();
    }

    @Override
    public Long removeSchedule(Long expertId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> ScheduleHandler.NOT_FOUND);

//        validateExpert(schedule, expertId);
        scheduleRepository.delete(schedule);
        return scheduleId;
    }

//    private void validateExpert(Schedule schedule, Long expertId) {
//        if (!schedule.getExpert().getId().equals(expertId)) {
//            throw new ScheduleHandler(ScheduleErrorStatus.SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT);
//        }
//    }
}
