package com.my_medi.domain.expert.service;

import com.my_medi.api.career.mapper.CareerConverter;
import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.common.util.EnumConvertUtil;
import com.my_medi.domain.career.repository.CareerRepository;
import com.my_medi.domain.career.service.CareerCommandService;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ExpertCommandServiceImpl implements ExpertCommandService {
    private final ExpertRepository expertRepository;
    private final PasswordEncoder passwordEncoder;
    private final CareerRepository careerRepository;

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
                .licenseFileUrl(registerExpertDto.getLicenseFileUrl())
                .introduction(registerExpertDto.getIntroduction())
                .build();
        expertRepository.save(expert); // ID가 생겨야 FK 설정 가능

        // 커리어 리스트 저장: dto -> entity 변환 후 저장
        careerRepository.saveAll(
                registerExpertDto.getCareers().stream()
                        .map(careerDto -> CareerConverter.toEntity(careerDto, expert))
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

    @Override
    public void registerDummyExperts(int count) {
        List<Expert> expertList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            expertList.add(
                    Expert.builder()
                            //member
                            .name("전문가" + i)
                            .birthDate(LocalDate.of(2000, 6,23))
                            .gender(Gender.MALE)
                            .username(UUID.randomUUID().toString())
                            .email(UUID.randomUUID().toString().substring(0, 7) + i + "@gmail.com")
                            .phoneNumber("010.1233.1233")
                            .profileImgUrl(null)
                            .role(Role.EXPERT) //role은 입력 x, EXPERT로 고정
                            .loginId("expert" + UUID.randomUUID().toString().substring(0, 5))
                            .password(passwordEncoder.encode("string"))
                            //Expert
                            .specialty(EnumConvertUtil.getRandomSpecialty())
                            .organizationName("MY-MEDI")
                            .licenseFileUrl(null)
                            .introduction("마이 메디 소속 전문가입니다ㅏ")
                            .build()
            );
        }
        expertRepository.saveAll(expertList);
    }

}