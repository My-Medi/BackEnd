package com.my_medi.domain.expert.service;

import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.common.util.EnumConvertUtil;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.career.repository.CareerRepository;
import com.my_medi.api.expert.dto.UpdateProfileDto;
import com.my_medi.api.expert.dto.UpdateResumeDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;

import com.my_medi.domain.license.repository.LicenseRepository;
import com.my_medi.domain.licenseImage.repository.LicenseImageRepository;

import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
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
    private final LicenseRepository licenseRepository;
    private final LicenseImageRepository licenseImageRepository;


    @Override
    public Long registerExpert(RegisterExpertDto registerExpertDto) {

        Expert expert = Expert.builder()
                //member
                .name(registerExpertDto.getMember().getName())
                .birthDate(registerExpertDto.getMember().getBirthDate())
                .gender(registerExpertDto.getMember().getGender())
                .username(UUID.randomUUID().toString())
                .nickname(registerExpertDto.getMember().getNickname())
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
                .introSentence(registerExpertDto.getIntroSentence())

                .build();
        expertRepository.save(expert); // ID가 생겨야 FK 설정 가능

        // 커리어 리스트 저장: dto -> entity 변환 후 저장
        careerRepository.saveAll(
                registerExpertDto.getCareers().stream()
                        .map(careerDto -> careerDto.toEntity(expert))
                        .collect(Collectors.toList())
        );
        // 자격증 이미지 저장
        licenseImageRepository.saveAll(
                registerExpertDto.getLicenseImages().stream()
                        .map(imageDto -> imageDto.toEntity(expert))
                        .collect(Collectors.toList())
        );

        // 자격증 리스트 저장
        licenseRepository.saveAll(
                registerExpertDto.getLicenses().stream()
                        .map(licenseDto -> licenseDto.toEntity(expert))
                        .collect(Collectors.toList())
        );



        return expert.getId();
    }


    // 피그마 [회원정보 수정페이지-전문가] 페이지
    @Override
    public Long updateProfile(Long expertId, UpdateProfileDto updateProfileDto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expert.modifyExpertInfo(updateProfileDto);
        return expert.getId();
    }

    // 피그마 [이력서 관리] 페이지
    @Override
    public Long updateResume(Long expertId, UpdateResumeDto updateResumeDto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND);
        expert.modifyResumeInfo(updateResumeDto);
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
        List<Career> careerList = new ArrayList<>();
        List<License> licenseList = new ArrayList<>();
        List<LicenseImage> licenseImageList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Expert expert = Expert.builder()
                    //member
                    .name("전문가" + i)
                    .birthDate("000623")
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
                    .introduction("마이 메디 소속 전문가입니다ㅏ")
                    .build();
            expertList.add(expert);

            careerList.add(Career.builder()
                    .expert(expert)
                    .companyName("MY-MEDI")
                    .jobTitle("medical")
                    .startDate(LocalDate.of(2024, 3,8))
                    .endDate(LocalDate.of(2025, 6, 9))
                    .build());

            licenseList.add(License.builder()
                    .licenseName("medical license")
                    .licenseDate(LocalDate.of(2025, 3,4))
                    .licenseDescription("nice medic")
                    .expert(expert)
                    .build());
        }
        expertRepository.saveAll(expertList);
        careerRepository.saveAll(careerList);
        licenseRepository.saveAll(licenseList);
    }

}