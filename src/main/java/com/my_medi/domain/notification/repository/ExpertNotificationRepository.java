package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    List<ExpertNotification> findByExpertId(Long expertId);

    List<ExpertNotification> findByExpertIdAndIsReadFalseOrderByIdDesc(Long expertId);

    List<ExpertNotification> findByExpertIdAndIsReadTrueOrderByIdDesc(Long expertId, Pageable pageable);

    Long countByExpertIdAndIsReadTrue(Long expertId);
}
