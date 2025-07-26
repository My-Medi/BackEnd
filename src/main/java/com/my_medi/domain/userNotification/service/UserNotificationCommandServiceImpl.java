package com.my_medi.domain.userNotification.service;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.userNotification.entity.NotificationType;
import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotificationCommandServiceImpl implements UserNotificationCommandService {
    private final UserRepository userRepository;
    private final ConsultationRequestRepository consultationRequestRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public void sendNotificationToUser(Long userId, Long sourceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        ConsultationRequest request = consultationRequestRepository.findById(sourceId)
                .orElseThrow(() -> ConsultationRequestHandler.NOT_FOUND);

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationContent(request.getComment())
                .sourceId(sourceId)
                .notificationType(NotificationType.CONSULTATION_RESPONSE)
                .build();

        userNotificationRepository.save(userNotification);
    }

    @Override
    public void removeNotification(Long notificationId) {
        userNotificationRepository.deleteById(notificationId);
    }
}
