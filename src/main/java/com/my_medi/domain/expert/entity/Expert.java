package com.my_medi.domain.expert.entity;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.dto.UpdateProfileDto;
import com.my_medi.domain.expert.dto.UpdateResumeDto;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.licenseImage.entity.LicenseImage;
import com.my_medi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue(StaticVariable.EXPERT)
public class Expert extends Member {

    //전문분야(enum)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    //소속 회사 기관명
    private String organizationName;

    // 자격증 증명사진(리스트)
    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LicenseImage> licenseImages;

    // 자격증(리스트)
    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<License> licenses = new ArrayList<>();

    // 경력사항(리스트)
    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers = new ArrayList<>();

    // 자기소개
    // 데이터베이스에 입력될 텍스트 크기를 길게 설정
    @Column(columnDefinition = "TEXT")
    private String introduction;

    // 나를 소개하는 대표 문장 한줄
    @Column
    private String IntroSentence;


    public void modifyExpertInfo(UpdateProfileDto dto) {
        //user 공통
        this.modifyMemberInfoExpert(dto);
    }

    public void modifyResumeInfo(UpdateResumeDto dto) {
        this.specialty = dto.getSpecialty(); // 전문분야
        this.organizationName = dto.getOrganizationName(); //소속 회사, 기관명
        this.introduction = dto.getIntroduction(); // 자기소개
        this.IntroSentence = dto.getIntroSentences(); // 나를 소개하는 대표 문장 한줄

        // 경력 초기화 후 다시 추가
        this.careers.clear();
        dto.getCareers().forEach(careerRequestDto -> this.careers.add(careerRequestDto.toEntity(this)));

        // 자격증 초기화 후 다시 추가
        this.licenses.clear();
        dto.getLicenses().forEach(licenseDto -> this.licenses.add(licenseDto.toEntity(this)));

        // 자격증 이미지 초기화 후 다시 추가
        this.licenseImages.clear();
        dto.getLicenseImages().forEach(licenseImageDto -> this.licenseImages.add(licenseImageDto.toEntity(this)));

    }

}
