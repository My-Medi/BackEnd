package com.my_medi.domain.notification.service;

import com.my_medi.domain.notification.entity.ExpertNotification;
import com.my_medi.domain.notification.repository.ExpertNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpertNotificationQueryServiceImpl implements ExpertNotificationQueryService {
    private final ExpertNotificationRepository expertNotificationRepository;

    @Override
    public Page<ExpertNotification> getExpertNotificaitonListByPage(Long expertId, Pageable pageable) {
        return expertNotificationRepository.findAllByExpertId(expertId, pageable);
    }
}
