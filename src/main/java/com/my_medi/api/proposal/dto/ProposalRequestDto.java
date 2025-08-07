package com.my_medi.api.proposal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposalRequestDto {
    private String lifeDescription;
    private HealthInterestsDto healthInterestsDto;
    private AbnormalValueDto abnormalValueDto;
    private HelpTopicDto helpTopicDto;
    private String goal;
    private String requestNote;
}