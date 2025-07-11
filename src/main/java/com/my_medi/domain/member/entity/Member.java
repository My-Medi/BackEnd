package com.my_medi.domain.member.entity;

import com.my_medi.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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

    //아이디(제약조건 : 8글자 이상)
    @Column(name = "login_id", nullable = false, unique = true)
    @Size(min = 8, message = "아이디는 최소 8자 이상이어야 합니다.")
    private String loginId;

    //비밀번호(제약조건 : 8글자 이상)
    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

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


}
