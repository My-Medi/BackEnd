package com.my_medi.api.expertNotification.service;

import com.my_medi.common.annotation.UseCase;
import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.service.ExpertNotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import static com.my_medi.common.consts.StaticVariable.PAGINATION_SORTING_BY_ID;
import static com.my_medi.common.consts.StaticVariable.NOTIFICATION_READ;

@UseCase
@RequiredArgsConstructor
public class ExpertNotificationUseCase {
    private final ExpertNotificationQueryService expertNotificationQueryService;

    public Page<ExpertNotification> getExpertNotifications
            (Long expertId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.asc(NOTIFICATION_READ), Sort.Order.desc(PAGINATION_SORTING_BY_ID))
        );

        return expertNotificationQueryService.getExpertNotificationListByPage(expertId, pageable);
    }
}
