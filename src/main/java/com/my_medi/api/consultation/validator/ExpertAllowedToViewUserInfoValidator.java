package com.my_medi.api.consultation.validator;

import com.my_medi.common.annotation.Validator;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Validator
@RequiredArgsConstructor
public class ExpertAllowedToViewUserInfoValidator {
    private final ConsultationRequestRepository consultationRequestRepository;

    public void validateMatchStatus(Long expertId, Long userId, RequestStatus status) {
        boolean isExisted = consultationRequestRepository
                .existsByExpertIdAndUserIdAndRequestStatus(expertId, userId, status);
        if (!isExisted) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }

    public void validateStatusIn(Long expertId, Long userId, List<RequestStatus> statusList) {
        boolean isIncluded = consultationRequestRepository
                .existsByUserIdAndExpertIdAndRequestStatusIn(userId, expertId, statusList);
        for (RequestStatus requestStatus : statusList) {
            log.info("status : {}", requestStatus);
        }
        log.info("isIncluded : {}", isIncluded);
        if (!isIncluded) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }
    }
}
