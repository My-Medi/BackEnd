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
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        return userNotificationQueryService.getUserNotificationListByPage(userId, pageable);
    }
}
