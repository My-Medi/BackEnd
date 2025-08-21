package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    Page<UserNotification> findAllByUserId(Long userId, Pageable pageable);
    void deleteByUserId(Long userId);
}
