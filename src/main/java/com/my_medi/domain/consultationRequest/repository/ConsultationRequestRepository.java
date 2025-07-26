package com.my_medi.domain.consultationRequest.repository;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRequestRepository extends JpaRepository<ConsultationRequest, Long> {

    List<ConsultationRequest> findByExpertId(Long expertId);

    List<ConsultationRequest> findByExpertIdAndRequestStatus(Long expertId, RequestStatus requestStatus);

    boolean existsByExpertIdAndUserIdAndRequestStatusNot(Long expertId, Long userId, RequestStatus requestStatus);

    long countByUserIdAndExpertId(Long userId, Long expertId);

    boolean existsByUserIdAndExpertIdAndRequestStatusIn(Long userId, Long expertId, List<RequestStatus> statuses);

    List<ConsultationRequest> findByUserId(Long userId);

    List<ConsultationRequest> findByUserIdAndRequestStatus(Long userId, RequestStatus requestStatus);

    List<ConsultationRequest> findByExpertIdAndUserId(Long expertId, Long userId);

    boolean existsByExpertIdAndUserIdAndRequestStatus(Long expertId, Long userId, RequestStatus requestStatus);

}
