package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.ProposalRequestDto;
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
    public Long writeProposal(Long userId, ProposalRequestDto proposalRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
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
                .build();

        proposalRepository.save(proposal);
        return proposal.getId();
    }

    @Override
    public Long editProposal(Long userId, ProposalRequestDto proposalRequestDto) {
        Proposal proposal = proposalRepository.findByUserId(userId)
                .orElseThrow(() -> ProposalHandler.NOT_FOUND);

        proposal.updateLifeDescriptionnGoal(proposalRequestDto);
        proposal.updateHealthInterests(proposalRequestDto);
        proposal.updateAbnormalValue(proposalRequestDto);
        proposal.updateHelpTopic(proposalRequestDto);

        return proposal.getId();
    }
}
