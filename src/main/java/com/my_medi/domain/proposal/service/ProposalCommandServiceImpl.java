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

    /**
     * Creates a new proposal for the specified user using the provided proposal data.
     *
     * Retrieves the user by ID, constructs a new Proposal entity with details from the given ProposalDto,
     * saves it to the repository, and returns the ID of the newly created proposal.
     *
     * @param userId the ID of the user for whom the proposal is being created
     * @param proposalDto the data transfer object containing proposal details
     * @return the ID of the newly created proposal
     * @throws UserHandler.NOT_FOUND if the user with the specified ID does not exist
     */
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

    /**
     * Updates an existing proposal for the specified user with new information.
     *
     * Retrieves the proposal associated with the given user ID, updates its life description, goal, health interests, abnormal values, and help topics using the provided data, and returns the proposal's ID.
     *
     * @param userId the ID of the user whose proposal is to be updated
     * @param proposalDto the data transfer object containing updated proposal information
     * @return the ID of the updated proposal
     * @throws ProposalHandler.NOT_FOUND if no proposal is found for the given user ID
     */
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
