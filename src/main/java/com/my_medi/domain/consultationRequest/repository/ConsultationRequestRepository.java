package com.my_medi.domain.consultationRequest.repository;

import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultationRequestRepository extends JpaRepository<ConsultationRequest, Long> {

    boolean existsByExpertIdAndUserIdAndRequestStatusNot(Long expertId, Long userId, RequestStatus requestStatus);

    long countByUserIdAndExpertId(Long userId, Long expertId);

    boolean existsByUserIdAndExpertIdAndRequestStatusIn(Long userId, Long expertId, List<RequestStatus> statuses);

    List<ConsultationRequest> findByUserId(Long userId);

    Page<ConsultationRequest> findByExpertId(Long expertId, Pageable pageable);

    Page<ConsultationRequest> findByExpertIdAndRequestStatus(Long expertId, RequestStatus requestStatus, Pageable pageable);

    List<ConsultationRequest> findByUserIdAndRequestStatus(Long userId, RequestStatus requestStatus);

    boolean existsByExpertIdAndUserIdAndRequestStatus(Long expertId, Long userId, RequestStatus requestStatus);

    List<ConsultationRequest> findByUserIdAndExpertIdAndRequestStatus(Long userId, Long expertId, RequestStatus requestStatus);

    @Query("""
                SELECT r FROM ConsultationRequest r
                WHERE r.expert.id = :expertId
                  AND r.user.id = :userId
                  AND r.requestStatus = :status
                ORDER BY r.createdDate DESC
            """)
    List<ConsultationRequest> findLatestRequestedByExpert(
            @Param("userId") Long userId,
            @Param("expertId") Long expertId,
            @Param("status") RequestStatus status,
            Pageable pageable);


    @Query("SELECT r FROM ConsultationRequest r " +
            "WHERE r.user.id = :userId AND r.expert.id = :expertId AND r.requestStatus = :status")
    Optional<ConsultationRequest> findMatchedRequest(@Param("userId") Long userId,
                                                     @Param("expertId") Long expertId,
                                                     @Param("status") RequestStatus status);


    @Query("SELECT r.createdDate FROM ConsultationRequest r " +
            "WHERE r.user.id = :userId AND r.expert.id = :expertId AND r.requestStatus = :status " +
            "ORDER BY r.createdDate DESC")
    List<LocalDateTime> findLatestRequestedDates(
            @Param("userId") Long userId,
            @Param("expertId") Long expertId,
            @Param("status") RequestStatus status,
            Pageable pageable
    );

    interface RequestedAgg {
        long getCount();
        LocalDateTime getLatestCreated();
    }

    @Query("""
                select count(r) as count,
                       max(r.createdDate) as latestCreated
                from ConsultationRequest r
                where r.user.id = :userId
                  and r.expert.id = :expertId
                  and r.requestStatus = :status
            """)
    ConsultationRequestRepository.RequestedAgg findRequestedAgg(
            @Param("userId") Long userId,
            @Param("expertId") Long expertId,
            @Param("status") RequestStatus status
    );

    int deleteByIdAndUserIdAndRequestStatus(Long id, Long userId, RequestStatus status);





}
