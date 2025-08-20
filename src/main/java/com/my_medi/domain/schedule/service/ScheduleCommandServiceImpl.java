package com.my_medi.domain.schedule.service;


import com.my_medi.api.schedule.dto.EditScheduleDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.exception.ScheduleErrorStatus;
import com.my_medi.domain.schedule.exception.ScheduleHandler;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO exception form unify
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {

    private final ScheduleRepository scheduleRepository;
    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;
    private final ConsultationRequestRepository consultationRequestRepository;


    @Override
    public Long registerScheduleToUser(Expert expert, Long userId, RegisterScheduleDto registerScheduleDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ScheduleHandler.NOT_FOUND);

        boolean isMatched = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expert.getId(), user.getId(), RequestStatus.ACCEPTED);

        if (!isMatched) {
            throw ScheduleHandler.MISMATCHED_CONSULTATION;
        }

        Schedule schedule = Schedule.builder()
                .expert(expert)
                .user(user)
                .title(registerScheduleDto.getTitle())
                .location(registerScheduleDto.getLocation())
                .meetingDate(registerScheduleDto.getMeetingDate())
                .hour(registerScheduleDto.getHour())
                .minute(registerScheduleDto.getMinute())
                .isAm(registerScheduleDto.isAm())
                .memo(registerScheduleDto.getMemo())
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

    @Override
    public void createScheduleDummy(Long expertId, Long userId, int year, int month, int count) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ScheduleHandler.NOT_FOUND);
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);

        boolean isMatched = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expertId, userId, RequestStatus.ACCEPTED);

        if (!isMatched) {
            throw ScheduleHandler.MISMATCHED_CONSULTATION;
        }

        List<Schedule> scheduleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            scheduleList.add(
                    Schedule.builder()
                            .expert(expert)
                            .user(user)
                            .title("dummy title")
                            .location("kwang-woon univ")
                            .meetingDate(LocalDate.of(year, month, new Random().nextInt(30) + 1))
                            .hour(1)
                            .minute(0)
                            .isAm(false)
                            .memo("dummy memo" + i)
                            .build()
            );
        }
        scheduleRepository.saveAll(scheduleList);
    }

//    private void validateExpert(Schedule schedule, Long expertId) {
//        if (!schedule.getExpert().getId().equals(expertId)) {
//            throw new ScheduleHandler(ScheduleErrorStatus.SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT);
//        }
//    }


}
