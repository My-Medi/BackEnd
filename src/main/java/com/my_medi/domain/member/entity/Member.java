package com.my_medi.domain.member.entity;

import com.my_medi.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //마이페이지 내 닉네임(전문가, user 동일하므로 member에 삽입)
    @Column(unique = true, nullable = true) // 회원가입시 입력받지 않으므로 기본 null
    private String nickname;

    //실제 이름
    @Column(nullable = false)
    private String last_name; // 성

    @Column(nullable = false)
    private String first_name;// 이름

    //회원가입
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true) // 회원가입시 입력받지 않으므로 기본 null
    private String profileImgUrl;


}
