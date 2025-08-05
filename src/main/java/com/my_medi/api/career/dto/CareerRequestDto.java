package com.my_medi.api.career.dto;

import com.my_medi.domain.career.entity.Career;
import com.my_medi.domain.expert.entity.Expert;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CareerRequestDto {
    private String companyName;
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate endDate;

    public Career toEntity(Expert expert) {
        return Career.builder()
                .expert(expert)
                .companyName(this.companyName)
                .jobTitle(this.jobTitle)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
