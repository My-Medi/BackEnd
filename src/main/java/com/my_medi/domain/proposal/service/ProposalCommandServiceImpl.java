package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.exception.ProposalHandler;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalCommandServiceImpl implements ProposalCommandService {
    private final ProposalRepository proposalRepository;

    @Override
    public Long writeProposal(User user, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = Proposal.builder()
                .user(user)
                .lifeDescription(proposalRequestDto.getLifeDescription())

                .weightManagement(proposalRequestDto.getHealthInterestsDto().getWeightManagement())
                .bloodSugarControl(proposalRequestDto.getHealthInterestsDto().getBloodSugarControl())
                .cholesterolControl(proposalRequestDto.getHealthInterestsDto().getCholesterolControl())
                .bloodPressureControl(proposalRequestDto.getHealthInterestsDto().getBloodPressureControl())
                .liverFunctionCare(proposalRequestDto.getHealthInterestsDto().getLiverFunctionCare())
                .sleepRecovery(proposalRequestDto.getHealthInterestsDto().getSleepRecovery())
                .dietImprovement(proposalRequestDto.getHealthInterestsDto().getDietImprovement())
                .exerciseRoutine(proposalRequestDto.getHealthInterestsDto().getExerciseRoutine())
                .stressAndLifestyle(proposalRequestDto.getHealthInterestsDto().getStressAndLifestyle())

                .fastingBloodSugar(proposalRequestDto.getAbnormalValueDto().getFastingBloodSugar())
                .cholesterolLdl(proposalRequestDto.getAbnormalValueDto().getCholesterolLdl())
                .bloodPressure(proposalRequestDto.getAbnormalValueDto().getBloodPressure())
                .liverEnzymes(proposalRequestDto.getAbnormalValueDto().getLiverEnzymes())
                .bmiOrBodyFat(proposalRequestDto.getAbnormalValueDto().getBmiOrBodyFat())
                .noHealthCheckResult(proposalRequestDto.getAbnormalValueDto().getNoHealthCheckResult())

                .dietitian(proposalRequestDto.getHelpTopicDto().getDietitian())
                .healthManager(proposalRequestDto.getHelpTopicDto().getHealthManager())
                .wellnessCoach(proposalRequestDto.getHelpTopicDto().getWellnessCoach())
                .exerciseTherapist(proposalRequestDto.getHelpTopicDto().getExerciseTherapist())
                .recommendForMe(proposalRequestDto.getHelpTopicDto().getRecommendForMe())
                .goal(proposalRequestDto.getGoal())
                .requestNote(proposalRequestDto.getRequestNote())
                .build();

        proposalRepository.save(proposal);

        return proposal.getId();
    }

    @Override
    public Long editProposal(User user, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = proposalRepository.findByUserId(user.getId())
                .orElseThrow(() -> ProposalHandler.NOT_FOUND);

        proposal.updateUserDetails(proposalRequestDto);
        proposal.updateHealthInterests(proposalRequestDto);
        proposal.updateAbnormalValue(proposalRequestDto);
        proposal.updateHelpTopic(proposalRequestDto);

        return proposal.getId();
    }
}
