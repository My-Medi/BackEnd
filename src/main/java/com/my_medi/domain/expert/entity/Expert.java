package com.my_medi.domain.expert.entity;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.license.entity.License;
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


    public void modifyExpertInfo(UpdateExpertDto dto) {
        //user 공통
        this.modifyMemberInfoExpert(dto);

        //expert
        this.specialty = dto.getSpecialty();
        this.organizationName = dto.getOrganizationName();
        this.introduction = dto.getIntroduction();

    }

}
