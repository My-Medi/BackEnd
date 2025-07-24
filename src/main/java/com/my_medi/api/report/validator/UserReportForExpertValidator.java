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
                consultationRequestRepository.findByExpertIdAndUserId(expertId, userId);

        boolean matched = acceptedRequests.stream()
                .anyMatch(request -> request.getRequestStatus() == RequestStatus.ACCEPTED);

        if (!matched) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }
}
