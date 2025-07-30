package com.my_medi.domain.career.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class CareerDto {
    private String companyName;    // 회사명
    private String jobTitle;       // 직무
    private LocalDate startDate;   // 근무 시작일
    private LocalDate endDate;     // 근무 종료일
}
