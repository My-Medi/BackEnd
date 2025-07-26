package com.my_medi.domain.expertNotification.repository;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    List<ExpertNotification> findByExpertId(Long expertId);

    @Query("SELECT n FROM ExpertNotification n JOIN FETCH n.expert WHERE n.expert.id = :expertId")
    List<ExpertNotification> findWithExpertByExpertId(@Param("expertId") Long expertId);
}
