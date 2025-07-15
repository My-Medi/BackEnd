package com.my_medi.domain.proposal.service;

import com.my_medi.api.proposal.dto.WriteProposalDto;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.exception.ProposalHandler;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.user.entity.User;
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
    public Long writeProposal(Long userId, WriteProposalDto writeProposalDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProposalHandler(ErrorStatus.USER_NOT_FOUND));
        Proposal proposal = Proposal.builder()
                .user(user)
                .lifeDescription(writeProposalDto.getLifeDescription())
                .weightManagement(writeProposalDto.getHealthInterestsDto().getWeightManagement())
                .bloodSugarControl(writeProposalDto.getHealthInterestsDto().getBloodSugarControl())
                .cholesterolControl(writeProposalDto.getHealthInterestsDto().getCholesterolControl())
                .bloodPressureControl(writeProposalDto.getHealthInterestsDto().getBloodPressureControl())
                .liverFunctionCare(writeProposalDto.getHealthInterestsDto().getLiverFunctionCare())
                .sleepRecovery(writeProposalDto.getHealthInterestsDto().getSleepRecovery())
                .dietImprovement(writeProposalDto.getHealthInterestsDto().getDietImprovement())
                .exerciseRoutine(writeProposalDto.getHealthInterestsDto().getExerciseRoutine())
                .stressAndLifestyle(writeProposalDto.getHealthInterestsDto().getStressAndLifestyle())

                .fastingBloodSugar(writeProposalDto.getAbnormalValueDto().getFastingBloodSugar())
                .cholesterolLdl(writeProposalDto.getAbnormalValueDto().getCholesterolLdl())
                .bloodPressure(writeProposalDto.getAbnormalValueDto().getBloodPressure())
                .liverEnzymes(writeProposalDto.getAbnormalValueDto().getLiverEnzymes())
                .bmiOrBodyFat(writeProposalDto.getAbnormalValueDto().getBmiOrBodyFat())
                .noHealthCheckResult(writeProposalDto.getAbnormalValueDto().getNoHealthCheckResult())

                .dietitian(writeProposalDto.getHelpTopicDto().getDietitian())
                .healthManager(writeProposalDto.getHelpTopicDto().getHealthManager())
                .wellnessCoach(writeProposalDto.getHelpTopicDto().getWellnessCoach())
                .exerciseTherapist(writeProposalDto.getHelpTopicDto().getExerciseTherapist())
                .recommendForMe(writeProposalDto.getHelpTopicDto().getRecommendForMe())
                .goal(writeProposalDto.getGoal())
                .build();

        proposalRepository.save(proposal);
        return proposal.getId();
    }

    @Override
    public Long editProposal(Long userId, WriteProposalDto writeProposalDto) {
        Proposal proposal = proposalRepository.findByUserId(userId)
                .orElseThrow(() -> new ProposalHandler(ErrorStatus.PROPOSAL_NOT_FOUND));
        proposal.updateHealthInterests(writeProposalDto);
        proposal.updateAbnormalValue(writeProposalDto);
        proposal.updateHelpTopic(writeProposalDto);
        return proposal.getId();
    }
}
