package com.my_medi.api.consultation.validator;

import com.my_medi.common.annotation.Validator;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Validator
@RequiredArgsConstructor
public class ExpertAllowedToViewUserInfoValidator {
    private final ConsultationRequestRepository consultationRequestRepository;

    public void validateMatchStatus(Long expertId, Long userId, RequestStatus status) {
        boolean approved = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expertId, userId, status);
        if (!approved) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }
}
