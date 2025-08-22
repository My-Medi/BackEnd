package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.ExpertNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    Page<ExpertNotification> findAllByExpertId(Long expertId, Pageable pageable);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ExpertNotification n where n.expert.id = :expertId")
    void deleteAllByExpertId(@Param("expertId") Long expertId);
}
