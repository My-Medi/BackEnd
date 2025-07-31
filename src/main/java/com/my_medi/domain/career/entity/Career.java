package com.my_medi.domain.career.entity;

import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.expert.dto.UpdateExpertDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 혹은 AUTO
    private Long id;
    // 회사/기관명
    private String companyName;
    // 근무기간
    private LocalDate startDate; // 근무기간(시작)
    private LocalDate endDate; // 근무기간(끝)
    // 역할
    private String jobTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert; // 1:N 관계 설정

    public void update(CareerResponseDto dto) {
        this.companyName = dto.getCompanyName();
        this.jobTitle = dto.getJobTitle();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }


}

