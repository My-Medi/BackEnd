package com.my_medi.api.userNotification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.service.UserNotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationUseCase {
    private final UserNotificationQueryService userNotificationQueryService;

    public Page<UserNotification> getPrioritizedNotificationDtoSliceByUserId
            (Long userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.asc("isRead"), Sort.Order.desc("id")) // 안 읽은 알림 우선 + 최신순
        );

        return userNotificationQueryService.getUserNotificationListByPage(userId, pageable);
    }
}
