package com.my_medi.api.proposal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelpTopicDto {

    private Boolean dietitian;
    private Boolean healthManager;
    private Boolean wellnessCoach;
    private Boolean exerciseTherapist;
    private Boolean recommendForMe;
}
