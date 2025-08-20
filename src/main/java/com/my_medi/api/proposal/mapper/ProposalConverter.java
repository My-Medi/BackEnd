package com.my_medi.api.proposal.mapper;

import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.api.proposal.dto.ProposalResponseDto.UserProposalDto;
import com.my_medi.api.proposal.dto.AbnormalValueDto;
import com.my_medi.api.proposal.dto.HealthInterestsDto;
import com.my_medi.api.proposal.dto.HelpTopicDto;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.user.entity.User;

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
                .requestNote(proposal.getRequestNote())
                .build();
    }

    private static HealthInterestsDto toHealthInterestsDto(Proposal proposal) {
        return HealthInterestsDto.builder()
                .weightManagement(proposal.getWeightManagement())
                .bloodSugarControl(proposal.getBloodSugarControl())
                .cholesterolControl(proposal.getCholesterolControl())
                .liverFunctionCare(proposal.getLiverFunctionCare())
                .sleepRecovery(proposal.getSleepRecovery())
                .dietImprovement(proposal.getDietImprovement())
                .exerciseRoutine(proposal.getExerciseRoutine())
                .stressAndLifestyle(proposal.getStressAndLifestyle())
                .other(proposal.getOther())
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

    // Dto -> Entity
    public static Proposal toEntity(User user, ProposalRequestDto dto) {
        return Proposal.builder()
                .user(user)
                .lifeDescription(dto.getLifeDescription())

                .weightManagement(dto.getHealthInterestsDto().getWeightManagement())
                .bloodSugarControl(dto.getHealthInterestsDto().getBloodSugarControl())
                .cholesterolControl(dto.getHealthInterestsDto().getCholesterolControl())
                .liverFunctionCare(dto.getHealthInterestsDto().getLiverFunctionCare())
                .sleepRecovery(dto.getHealthInterestsDto().getSleepRecovery())
                .dietImprovement(dto.getHealthInterestsDto().getDietImprovement())
                .exerciseRoutine(dto.getHealthInterestsDto().getExerciseRoutine())
                .stressAndLifestyle(dto.getHealthInterestsDto().getStressAndLifestyle())
                .other(dto.getHealthInterestsDto().getOther())

                .fastingBloodSugar(dto.getAbnormalValueDto().getFastingBloodSugar())
                .cholesterolLdl(dto.getAbnormalValueDto().getCholesterolLdl())
                .bloodPressure(dto.getAbnormalValueDto().getBloodPressure())
                .liverEnzymes(dto.getAbnormalValueDto().getLiverEnzymes())
                .bmiOrBodyFat(dto.getAbnormalValueDto().getBmiOrBodyFat())
                .noHealthCheckResult(dto.getAbnormalValueDto().getNoHealthCheckResult())

                .dietitian(dto.getHelpTopicDto().getDietitian())
                .healthManager(dto.getHelpTopicDto().getHealthManager())
                .wellnessCoach(dto.getHelpTopicDto().getWellnessCoach())
                .exerciseTherapist(dto.getHelpTopicDto().getExerciseTherapist())
                .recommendForMe(dto.getHelpTopicDto().getRecommendForMe())
                .goal(dto.getGoal())
                .requestNote(dto.getRequestNote())
                .build();
    }
}
