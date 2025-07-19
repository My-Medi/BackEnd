package com.my_medi.domain.expertNotification.repository;

import com.my_medi.domain.expertNotification.entity.ExpertNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpertNotificationRepository extends JpaRepository<ExpertNotification, Long> {
    /**
 * Retrieves all ExpertNotification entities associated with the specified expert ID.
 *
 * @param expertId the unique identifier of the expert
 * @return a list of ExpertNotification entities linked to the given expert ID
 */
List<ExpertNotification> findByExpertId(Long expertId);
}
