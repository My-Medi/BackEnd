package com.my_medi.api.proposal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthInterestsDto {

    private Boolean weightManagement;
    private Boolean bloodSugarControl;
    private Boolean cholesterolControl;
    private Boolean bloodPressureControl;
    private Boolean liverFunctionCare;
    private Boolean sleepRecovery;
    private Boolean dietImprovement;
    private Boolean exerciseRoutine;
    private Boolean stressAndLifestyle;
}