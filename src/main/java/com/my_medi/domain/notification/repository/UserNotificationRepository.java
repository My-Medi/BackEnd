package com.my_medi.domain.notification.repository;

import com.my_medi.domain.notification.entity.UserNotification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByUserId(Long userId);

    List<UserNotification> findByUserIdAndIsReadFalseOrderByIdDesc(Long userId);

    List<UserNotification> findByUserIdAndIsReadTrueOrderByIdDesc(Long userId, Pageable pageable);

    Long countByUserIdAndIsReadTrue(Long userId);

    UserNotification findByUserIdAndSourceId(Long userId, Long sourceId);
}
