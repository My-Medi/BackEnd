package com.my_medi.domain.userNotification.repository;

import com.my_medi.domain.userNotification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByUserId(Long userId);
}
