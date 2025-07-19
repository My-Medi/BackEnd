package com.my_medi.api.proposal.mapper;

import com.my_medi.api.proposal.dto.ProposalResponseDto.UserProposalDto;
import com.my_medi.api.proposal.dto.AbnormalValueDto;
import com.my_medi.api.proposal.dto.HealthInterestsDto;
import com.my_medi.api.proposal.dto.HelpTopicDto;
import com.my_medi.domain.proposal.entity.Proposal;

public class ProposalConverter {

    /**
     * Converts a Proposal entity into a UserProposalDto, mapping core fields and nested health-related attributes into their respective DTOs.
     *
     * @param proposal the Proposal entity to convert
     * @return a UserProposalDto representing the proposal and its associated health interests, abnormal values, and help topics
     */
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

    /**
     * Converts the health interest-related fields of a Proposal into a HealthInterestsDto.
     *
     * @param proposal the Proposal entity containing health interest data
     * @return a HealthInterestsDto representing the user's health interests
     */
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

    /**
     * Converts abnormal health value fields from a Proposal entity into an AbnormalValueDto.
     *
     * @param proposal the Proposal entity containing abnormal health value data
     * @return an AbnormalValueDto populated with fasting blood sugar, LDL cholesterol, blood pressure, liver enzymes, BMI or body fat, and health check result status
     */
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

    /**
     * Converts the help topic-related fields of a Proposal into a HelpTopicDto.
     *
     * @param proposal the Proposal entity containing help topic information
     * @return a HelpTopicDto populated with dietitian, health manager, wellness coach, exercise therapist, and recommendation flag from the proposal
     */
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
