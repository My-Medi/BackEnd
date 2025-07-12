package com.my_medi.domain.schedule.service;

import com.my_medi.api.schedule.dto.RegisterScheduleDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.member.entity.Member;
//import com.my_medi.domain.member.repository.MemberRepository;
import com.my_medi.domain.schedule.entity.Schedule;
import com.my_medi.domain.schedule.exception.ScheduleHandler;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
//import com.my_medi.global.payload.code.ErrorStatus;
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
    private final MemberRepository memberRepository;

    @Override
    public Long registerScheduleToUser(Long expertId, Long userId, RegisterScheduleDto dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new ScheduleHandler(ErrorStatus.EXPERT_NOT_FOUND));

        Member user = memberRepository.findById(userId)
                .orElseThrow(() -> new ScheduleHandler(ErrorStatus.USER_NOT_FOUND));

        Schedule schedule = Schedule.of(expert, user, dto);
        return scheduleRepository.save(schedule).getId();
    }

    @Override
    public Long editSchedule(Long expertId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleHandler(ErrorStatus.SCHEDULE_NOT_FOUND));

        validateExpert(schedule, expertId);

        // TODO: 수정 DTO 추가 시 적용
        // schedule.update(dto);
        return schedule.getId();
    }

    @Override
    public Long removeSchedule(Long expertId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleHandler(ErrorStatus.SCHEDULE_NOT_FOUND));

        validateExpert(schedule, expertId);
        scheduleRepository.delete(schedule);
        return scheduleId;
    }

    private void validateExpert(Schedule schedule, Long expertId) {
        if (!schedule.getExpert().getId().equals(expertId)) {
            throw new ScheduleHandler(ErrorStatus.SCHEDULE_ONLY_CAN_BE_TOUCHED_BY_EXPERT);
        }
    }
}
