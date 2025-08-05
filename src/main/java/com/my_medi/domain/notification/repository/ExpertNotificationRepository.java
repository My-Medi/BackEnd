package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    Page<ExpertNotification> findAllByExpertId(Long expertId, Pageable pageable);
}
