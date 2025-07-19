package com.my_medi.api.proposal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbnormalValueDto {
    private Boolean fastingBloodSugar;
    private Boolean cholesterolLdl;
    private Boolean bloodPressure;
    private Boolean liverEnzymes;
    private Boolean bmiOrBodyFat;
    private Boolean noHealthCheckResult;
}