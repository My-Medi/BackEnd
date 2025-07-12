package com.my_medi.domain.member.entity;

import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.model.entity.BaseTimeEntity;
import com.my_medi.domain.user.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Size;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    //역할(개인, 전문가 구분)
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //loginId -> username으로 변경(소셜로그인)
    @Column(name = "username", unique = true, updatable = false, nullable = false)
    private UUID username;

    //성명
    @Column(name = "name", nullable = false)
    private String name;

    //생년월일
    @Column(name = "birth_date")
    private LocalDate birthDate;

    //성별 필드 추가 (남자, 여자)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    //닉네임
    @Column(name = "nickname", unique = true, nullable = true) // 회원가입시 입력받지 않으므로 기본 null
    private String nickname;

    //이메일
    // TODO: email, password : 검증 어노테이션, presentation(web 혹은 api) 레이어에 좀 더 적합하므로 추후 수정
    @Column(name = "email", nullable = false)
    private String email;

    //연락처
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    //프로필 이미지
    @Column(name = "profile_img_url", nullable = true) // 회원가입시 입력받지 않으므로 기본 null
    private String profileImgUrl;


    //TODO : dto 통일
    //User dto 전용
    public void modifyMemberInfoUser(UpdateUserDto dto){
        this.name = dto.getName();
        this.birthDate = dto.getBirthDate();

        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhoneNumber();
        this.profileImgUrl = dto.getProfileImgUrl();
    }

    //Expert dto 전용
    public void modifyMemberInfoExpert(UpdateExpertDto dto){
        this.name = dto.getName();
        this.birthDate = dto.getBirthDate();
        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhoneNumber();
        this.profileImgUrl = dto.getProfileImgUrl();
    }

}
