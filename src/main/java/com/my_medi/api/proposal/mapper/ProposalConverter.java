package com.my_medi.api.proposal.mapper;

import com.my_medi.api.proposal.dto.ProposalResponseDto.UserProposalDto;
import com.my_medi.api.proposal.dto.AbnormalValueDto;
import com.my_medi.api.proposal.dto.HealthInterestsDto;
import com.my_medi.api.proposal.dto.HelpTopicDto;
import com.my_medi.domain.proposal.entity.Proposal;

public class ProposalConverter {
    // Entity -> Dto

    public static UserProposalDto toUserProposalDto(Proposal proposal) {
        return UserProposalDto.builder()
                .id(proposal.getId())
                .userId(proposal.getUser().getId())
                .lifeDescription(proposal.getLifeDescription())
                .healthInterestsDto(toHealthInterestsDto(proposal))
                .abnormalValueDto(toAbnormalValueDto(proposal))
                .helpTopicDto(toHelpTopicDto(proposal))
                .goal(proposal.getGoal())
                .build();
    }

    private static HealthInterestsDto toHealthInterestsDto(Proposal proposal) {
        return HealthInterestsDto.builder()
                .weightManagement(proposal.getWeightManagement())
                .bloodSugarControl(proposal.getBloodSugarControl())
                .cholesterolControl(proposal.getCholesterolControl())
                .bloodPressureControl(proposal.getBloodPressureControl())
                .liverFunctionCare(proposal.getLiverFunctionCare())
                .sleepRecovery(proposal.getSleepRecovery())
                .dietImprovement(proposal.getDietImprovement())
                .exerciseRoutine(proposal.getExerciseRoutine())
                .stressAndLifestyle(proposal.getStressAndLifestyle())
                .build();
    }

    private static AbnormalValueDto toAbnormalValueDto(Proposal proposal) {
        return AbnormalValueDto.builder()
                .fastingBloodSugar(proposal.getFastingBloodSugar())
                .cholesterolLdl(proposal.getCholesterolLdl())
                .bloodPressure(proposal.getBloodPressure())
                .liverEnzymes(proposal.getLiverEnzymes())
                .bmiOrBodyFat(proposal.getBmiOrBodyFat())
                .noHealthCheckResult(proposal.getNoHealthCheckResult())
                .build();
    }

    private static HelpTopicDto toHelpTopicDto(Proposal proposal) {
        return HelpTopicDto.builder()
                .dietitian(proposal.getDietitian())
                .healthManager(proposal.getHealthManager())
                .wellnessCoach(proposal.getWellnessCoach())
                .exerciseTherapist(proposal.getExerciseTherapist())
                .recommendForMe(proposal.getRecommendForMe())
                .build();
    }
}
