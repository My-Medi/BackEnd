package com.my_medi.domain.user.entity;

import com.my_medi.domain.Discriminator;
import com.my_medi.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorValue(Discriminator.USER)      //TODO ROLE ENUM key값으로
public class User extends Member {

    @Column(unique = true)
    private String userUuid;

    //키
    private Float height;

    //몸무게
    private Float weight;

    // TODO: 나중에 건강검진 리포트 기능 생기면 연관관계 추가
    // @OneToMany(mappedBy = "user")
    // private List<HealthReport> reports = new ArrayList<>();



}
