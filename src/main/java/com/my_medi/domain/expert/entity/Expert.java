package com.my_medi.domain.expert.entity;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue(StaticVariable.EXPERT)      //TODO ROLE ENUM key값으로
public class Expert extends Member {


    //전문분야(enum)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    //소속 회사 기관명
    private String organizationName;

    // 자격증
    // 데이터베이스에 입력될 텍스트 크기를 길게 설정
    @Column(length = 1000)
    private String licenseFileUrl;

    // 경력사항(추후 연관관계로 받아올 예정)
    // @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<경력테이블> careers = new ArrayList<>();

    //경력소개
    // 데이터베이스에 입력될 텍스트 크기를 길게 설정
    @Column(columnDefinition = "TEXT")
    private String introduction;

    public void modifyInfo(UpdateExpertDto dto) {
        //user 공통
        this.setName(dto.getName());
        this.setBirthDate(dto.getBirthDate());
        this.setNickname(dto.getNickname());
        this.setPhoneNumber(dto.getPhoneNumber());
        this.setProfileImgUrl(dto.getProfileImgUrl());

        //expert
        this.specialty = dto.getSpecialty();
        this.organizationName = dto.getOrganizationName();
        this.licenseFileUrl = dto.getLicenseFileUrl();
        this.introduction = dto.getIntroduction();
    }



}
