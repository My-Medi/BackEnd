package com.my_medi.domain.userNotification.repository;

import com.my_medi.domain.userNotification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    /**
 * Retrieves all user notifications associated with the specified user ID.
 *
 * @param userId the unique identifier of the user whose notifications are to be fetched
 * @return a list of UserNotification entities for the given user ID
 */
List<UserNotification> findByUserId(Long userId);
}
