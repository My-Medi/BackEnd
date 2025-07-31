package com.my_medi.domain.expertNotification.repository;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    List<ExpertNotification> findByExpertId(Long expertId);
}
