package com.my_medi.domain.consultationRequest.repository;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsultationRequestRepository extends JpaRepository<ConsultationRequest, Long> {

    List<ConsultationRequest> findByExpertId(Long expertId);

    List<ConsultationRequest> findByExpertIdAndRequestStatus(Long expertId, RequestStatus requestStatus);

    boolean existsByExpertIdAndUserIdAndRequestStatusNot(Long expertId, Long userId, RequestStatus requestStatus);

    long countByUserIdAndExpertId(Long userId, Long expertId);

    boolean existsByUserIdAndExpertIdAndRequestStatusIn(Long userId, Long expertId, List<RequestStatus> statuses);

    List<ConsultationRequest> findByUserId(Long userId);

    Page<ConsultationRequest> findByExpertId(Long expertId, Pageable pageable);

    Page<ConsultationRequest> findByExpertIdAndRequestStatus(Long expertId, RequestStatus requestStatus, Pageable pageable);

    List<ConsultationRequest> findByUserIdAndRequestStatus(Long userId, RequestStatus requestStatus);

    List<ConsultationRequest> findByExpertIdAndUserId(Long expertId, Long userId);

    boolean existsByExpertIdAndUserIdAndRequestStatus(Long expertId, Long userId, RequestStatus requestStatus);

    List<ConsultationRequest> findByUserIdAndExpertIdAndRequestStatus(Long userId, Long expertId, RequestStatus requestStatus);

    @Query("SELECT r FROM ConsultationRequest r " +
            "WHERE r.expert.id = :expertId AND r.user.id = :userId AND r.requestStatus = :status " +
            "ORDER BY r.createdDate DESC")
    List<ConsultationRequest> findLatestRequestedByExpert(@Param("userId") Long userId,
                                                          @Param("expertId") Long expertId,
                                                          @Param("status") RequestStatus status);


    @Query("SELECT r FROM ConsultationRequest r " +
            "WHERE r.user.id = :userId AND r.expert.id = :expertId AND r.requestStatus = :status")
    Optional<ConsultationRequest> findMatchedRequest(@Param("userId") Long userId,
                                                     @Param("expertId") Long expertId,
                                                     @Param("status") RequestStatus status);



}
