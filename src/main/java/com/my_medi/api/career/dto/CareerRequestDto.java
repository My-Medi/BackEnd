package com.my_medi.api.career.dto;

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
}
