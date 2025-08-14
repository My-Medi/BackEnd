package com.my_medi.api.userNotification.service;

import com.my_medi.domain.notification.entity.UserNotification;
import com.my_medi.domain.notification.service.UserNotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import static com.my_medi.common.consts.StaticVariable.USER_NOTIFICATION_ID;
import static com.my_medi.common.consts.StaticVariable.USER_NOTIFICATION_READ;

@Service
@RequiredArgsConstructor
public class UserNotificationUseCase {
    private final UserNotificationQueryService userNotificationQueryService;

    //TODO "isRead", "id" <- StaticVariables.class use
    public Page<UserNotification> getPrioritizedNotificationDtoPageByUserId
            (Long userId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.asc(USER_NOTIFICATION_READ), Sort.Order.desc(USER_NOTIFICATION_ID))
        );

        return userNotificationQueryService.getUserNotificationListByPage(userId, pageable);
    }
}
