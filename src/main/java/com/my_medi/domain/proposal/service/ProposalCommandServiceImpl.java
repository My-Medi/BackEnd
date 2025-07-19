package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalDto;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.exception.ProposalHandler;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalCommandServiceImpl implements ProposalCommandService {
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    @Override
    public Long writeProposal(Long userId, ProposalDto proposalDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        Proposal proposal = Proposal.builder()
                .user(user)
                .lifeDescription(proposalDto.getLifeDescription())
                .weightManagement(proposalDto.getHealthInterestsDto().getWeightManagement())
                .bloodSugarControl(proposalDto.getHealthInterestsDto().getBloodSugarControl())
                .cholesterolControl(proposalDto.getHealthInterestsDto().getCholesterolControl())
                .bloodPressureControl(proposalDto.getHealthInterestsDto().getBloodPressureControl())
                .liverFunctionCare(proposalDto.getHealthInterestsDto().getLiverFunctionCare())
                .sleepRecovery(proposalDto.getHealthInterestsDto().getSleepRecovery())
                .dietImprovement(proposalDto.getHealthInterestsDto().getDietImprovement())
                .exerciseRoutine(proposalDto.getHealthInterestsDto().getExerciseRoutine())
                .stressAndLifestyle(proposalDto.getHealthInterestsDto().getStressAndLifestyle())

                .fastingBloodSugar(proposalDto.getAbnormalValueDto().getFastingBloodSugar())
                .cholesterolLdl(proposalDto.getAbnormalValueDto().getCholesterolLdl())
                .bloodPressure(proposalDto.getAbnormalValueDto().getBloodPressure())
                .liverEnzymes(proposalDto.getAbnormalValueDto().getLiverEnzymes())
                .bmiOrBodyFat(proposalDto.getAbnormalValueDto().getBmiOrBodyFat())
                .noHealthCheckResult(proposalDto.getAbnormalValueDto().getNoHealthCheckResult())

                .dietitian(proposalDto.getHelpTopicDto().getDietitian())
                .healthManager(proposalDto.getHelpTopicDto().getHealthManager())
                .wellnessCoach(proposalDto.getHelpTopicDto().getWellnessCoach())
                .exerciseTherapist(proposalDto.getHelpTopicDto().getExerciseTherapist())
                .recommendForMe(proposalDto.getHelpTopicDto().getRecommendForMe())
                .goal(proposalDto.getGoal())
                .build();

        proposalRepository.save(proposal);
        return proposal.getId();
    }

    @Override
    public Long editProposal(Long userId, ProposalDto proposalDto) {
        Proposal proposal = proposalRepository.findByUserId(userId)
                .orElseThrow(() -> ProposalHandler.NOT_FOUND);

        proposal.updateLifeDescriptionnGoal(proposalDto);
        proposal.updateHealthInterests(proposalDto);
        proposal.updateAbnormalValue(proposalDto);
        proposal.updateHelpTopic(proposalDto);

        return proposal.getId();
    }
}
