package com.my_medi.domain.schedule.service;


import com.my_medi.api.schedule.dto.EditScheduleDto;
import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
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
    private final ConsultationRequestRepository consultationRequestRepository;


    @Override
    public Long registerScheduleToUser(Expert expert, Long userId, RegisterScheduleDto registerScheduleDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ScheduleHandler(ScheduleErrorStatus.USER_NOT_FOUND));

        boolean isMatched = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expert.getId(), user.getId(), RequestStatus.ACCEPTED);

        if (!isMatched) {
            throw new ScheduleHandler(ScheduleErrorStatus.NOT_MATCHED_CONSULTATION);
        }

        Schedule schedule = Schedule.builder()
                .expert(expert)
                .user(user)
                .title(registerScheduleDto.getTitle())
                .location(registerScheduleDto.getLocation())
                .date(registerScheduleDto.getDate())
                .hour(registerScheduleDto.getHour())
                .minute(registerScheduleDto.getMinute())
                .isAm(registerScheduleDto.isAm())
                .location(registerScheduleDto.getLocation())
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

//    private void validateExpert(Schedule schedule, Long expertId) {
//        if (!schedule.getExpert().getId().equals(expertId)) {
//            throw new ScheduleHandler(ScheduleErrorStatus.SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT);
//        }
//    }


}
