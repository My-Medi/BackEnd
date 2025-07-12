package com.my_medi.domain.userNotification.repository;

import com.my_medi.domain.userNotification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    Optional<UserNotification> findByUserId(Long userId);
}
