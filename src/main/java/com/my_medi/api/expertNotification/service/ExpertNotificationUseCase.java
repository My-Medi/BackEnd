package com.my_medi.api.expertNotification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.service.ExpertNotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertNotificationUseCase {
    private final ExpertNotificationQueryService expertNotificationQueryService;

    public Page<ExpertNotification> getPrioritizedNotificationDtoPageByExpertId
            (Long expertId, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(
                currentPage,
                pageSize,
                Sort.by(Sort.Order.asc("isRead"), Sort.Order.desc("id")) // 안 읽은 알림 우선 + 최신순
        );

        return expertNotificationQueryService.getExpertNotificationListByPage(expertId, pageable);
    }
}
