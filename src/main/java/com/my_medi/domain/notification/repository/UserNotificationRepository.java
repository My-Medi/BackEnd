package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByUserId(Long userId);
}
