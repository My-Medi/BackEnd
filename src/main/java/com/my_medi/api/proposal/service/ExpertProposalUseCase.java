package com.my_medi.api.proposal.service;

import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertProposalUseCase {
    private final ConsultationRequestRepository consultationRequestRepository;
    private final ProposalQueryService proposalQueryService;

    public ProposalResponseDto.UserProposalDto getUserProposalForExpert(Expert expert, Long userId) {
        List<ConsultationRequest> consultationRequests =
                consultationRequestRepository.findByExpertId(expert.getId());

        boolean hasAccepted = consultationRequests.stream()
                .anyMatch(request -> request.getRequestStatus() == RequestStatus.ACCEPTED);

        if (!hasAccepted) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }

        Proposal proposal = proposalQueryService.getProposalByUserId(userId);

        return ProposalConverter.toUserProposalDto(proposal);
    }
}

