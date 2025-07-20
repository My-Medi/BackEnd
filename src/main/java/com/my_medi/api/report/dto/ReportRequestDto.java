package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReportRequestDto {
    private LocalDate checkupDate;
    private Integer round;
    private Measurement measurement;
    private BloodPressure bloodPressure;
    private BloodTest bloodTest;
    private UrineTest urineTest;
    private ImagingTest imagingTest;
    private Interview interview;
    private AdditionalTest additionalTest;
}
