package com.my_medi.domain.user.service;
import com.my_medi.api.member.dto.RegisterMemberDto;

import com.my_medi.domain.advice.repository.AdviceRepository;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.api.user.dto.UpdateUserDto;
import com.my_medi.domain.notification.repository.UserNotificationRepository;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.reportResult.repository.ReportResultRepository;
import com.my_medi.domain.schedule.repository.ScheduleRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.user.exception.UserHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdviceRepository adviceRepository;
    private final ConsultationRequestRepository consultationRequestRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final ProposalRepository proposalRepository;
    private final ReportRepository reportRepository;
    private final ReportResultRepository reportResultRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public Long registerUser(RegisterMemberDto registerMemberDto){
        //TODO [LATER] requestDto에 맞게 구현예정
        User user = User.builder()
                .name(registerMemberDto.getName())
                .birthDate(registerMemberDto.getBirthDate())
                .gender(registerMemberDto.getGender())
                .nickname(registerMemberDto.getNickname())
                .username(UUID.randomUUID().toString())
                .email(registerMemberDto.getEmail())
                .phoneNumber(registerMemberDto.getPhoneNumber())
                .profileImgUrl(registerMemberDto.getProfileImgUrl())
                .role(Role.USER)
                .loginId(registerMemberDto.getLoginId())
                .password(passwordEncoder.encode(registerMemberDto.getPassword()))
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUserInformation(Long userId, UpdateUserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        user.modifyUserInfo(dto);
        return user.getId();
    }

    @Override
    public Long deleteUserAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        adviceRepository.deleteByUserId(userId);
        consultationRequestRepository.deleteByUserId(userId);
        userNotificationRepository.deleteByUserId(userId);
        proposalRepository.deleteByUserId(userId);

        reportResultRepository.deleteAllByUserId(userId);
        reportRepository.deleteAllByUserId(userId);

        scheduleRepository.deleteAllByUserId(userId);

        userRepository.delete(user); // TODO : Hard delete이나 추후 soft로 변경예정
        return user.getId();
    }
}

