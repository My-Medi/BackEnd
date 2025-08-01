package com.my_medi.domain.expert.service;

import com.my_medi.api.career.mapper.CareerConverter;
import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.license.mapper.LicenseConverter;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.career.repository.CareerRepository;
import com.my_medi.domain.career.service.CareerCommandService;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.license.repository.LicenseRepository;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ExpertCommandServiceImpl implements ExpertCommandService {
    private final ExpertRepository expertRepository;
    private final PasswordEncoder passwordEncoder;
    private final CareerRepository careerRepository;
    private final LicenseRepository licenseRepository;


    @Override
    public Long registerExpert(RegisterExpertDto registerExpertDto) {

        Expert expert = Expert.builder()
                //member
                .name(registerExpertDto.getMember().getName())
                .birthDate(registerExpertDto.getMember().getBirthDate())
                .gender(registerExpertDto.getMember().getGender())
                .username(UUID.randomUUID().toString())
                .email(registerExpertDto.getMember().getEmail())
                .phoneNumber(registerExpertDto.getMember().getPhoneNumber())
                .profileImgUrl(registerExpertDto.getMember().getProfileImgUrl())
                .role(Role.EXPERT) //role은 입력 x, EXPERT로 고정
                .loginId(registerExpertDto.getMember().getLoginId())
                .password(passwordEncoder.encode(registerExpertDto.getMember().getPassword()))
                //Expert
                .specialty(registerExpertDto.getSpecialty())
                .organizationName(registerExpertDto.getOrganizationName())
                .introduction(registerExpertDto.getIntroduction())
                .build();
        expertRepository.save(expert); // ID가 생겨야 FK 설정 가능

        // 커리어 리스트 저장: dto -> entity 변환 후 저장
        careerRepository.saveAll(
                registerExpertDto.getCareers().stream()
                        .map(careerDto -> CareerConverter.toEntity(careerDto, expert))
                        .collect(Collectors.toList())
        );

        // 자격증 리스트 저장
        licenseRepository.saveAll(
                registerExpertDto.getLicenses().stream()
                        .map(licenseDto -> LicenseConverter.toEntity(licenseDto, expert))
                        .collect(Collectors.toList())
        );

        return expert.getId();
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
        expertRepository.delete(expert); // TODO : hard delete이나 추후 soft delete 수정 예정
    }

}