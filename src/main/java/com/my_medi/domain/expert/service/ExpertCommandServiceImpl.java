package com.my_medi.domain.expert.service;

import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ExpertCommandServiceImpl implements ExpertCommandService {
    private final ExpertRepository expertRepository;

    @Override
    public Long registerExpert(RegisterExpertDto registerExpertDto) {

        RegisterMemberDto m = registerExpertDto.getMember();

        //TODO [LATER] requestDto에 맞게 argument 변경해주기
        Expert expert = Expert.builder()
                .name(m.getName())
                .birthDate(m.getBirthDate())
                .gender(m.getGender())
                .username(UUID.randomUUID().toString())
                .email(m.getEmail())
                .phoneNumber(m.getPhoneNumber())
                .profileImgUrl(m.getProfileImgUrl())
                .role(Role.EXPERT) //role은 입력 x, EXPERT로 고정

                .specialty(registerExpertDto.getSpecialty())
                .organizationName(registerExpertDto.getOrganizationName())
                .licenseFileUrl(registerExpertDto.getLicenseFileUrl())
                .introduction(registerExpertDto.getIntroduction())

                .build();
        return expertRepository.save(expert).getId();

    }


    @Override
    public Long updateExpertInformation(Long expertId, UpdateExpertDto dto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expert.modifyExpertInfo(dto);
        return expert.getId();
    }

    @Override
    public void deleteExpertAccount(Long expertId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expertRepository.delete(expert); // hard delete
    }

}