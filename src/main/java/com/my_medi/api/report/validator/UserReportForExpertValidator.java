package com.my_medi.api.report.validator;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserReportForExpertValidator {
    private final ConsultationRequestRepository consultationRequestRepository;

    public void validateExpertHasAcceptedUser(Long expertId, Long userId) {
        List<ConsultationRequest> acceptedRequests =
                consultationRequestRepository.findByExpertIdAndRequestStatus(expertId, RequestStatus.ACCEPTED);

        boolean matched = acceptedRequests.stream()
                .anyMatch(req -> req.getUser().getId().equals(userId));

        if (!matched) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }
}
