package com.my_medi.api.consultation.validator;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpertAllowedToViewUserInfoValidator {
    private final ConsultationRequestRepository consultationRequestRepository;

    //TODO validateExpertHasAcceptedUser & validateExpertHasRequestFromUser <- 하나로 통일
    public void validate(Long expertId, Long userId, RequestStatus status) {
        switch (status) {
            case ACCEPTED -> validateExpertHasAcceptedUser(expertId, userId);
            case REQUESTED -> validateExpertHasRequestFromUser(expertId, userId);
            default -> throw ConsultationRequestHandler.NOT_FOUND; // REJECTED 등
        }
    }
    /** 전문가-사용자 사이에 '수락됨(ACCEPTED)'이 존재하는지 확인 */
    public void validateExpertHasAcceptedUser(Long expertId, Long userId) {
        boolean approved = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expertId, userId, RequestStatus.ACCEPTED);
        if (!approved) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }

    /** 전문가-사용자 사이에 '요청됨(REQUESTED)'이 존재하는지 확인 */
    public void validateExpertHasRequestFromUser(Long expertId, Long userId) {
        boolean exists = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expertId, userId, RequestStatus.REQUESTED);
        if (!exists) throw ConsultationRequestHandler.NOT_FOUND;
    }
}
