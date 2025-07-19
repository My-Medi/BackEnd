package com.my_medi.domain.userNotification.service;

import com.my_medi.api.userNotification.dto.SendNotificationToUserDto;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.exception.UserNotificationHandler;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotificationCommandServiceImpl implements UserNotificationCommandService {
    private final UserRepository userRepository;
    private final UserNotificationRepository userNotificationRepository;

    @Override
    public Long sendNotificationToUser(Long userId, Long sourceId, SendNotificationToUserDto sendNotificationToUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notificationTitle(sendNotificationToUserDto.getTitle())
                .notificationContent(sendNotificationToUserDto.getContent())
                .sourceId(sourceId)
                .build();

        userNotificationRepository.save(userNotification);

        return userNotification.getId();
    }

    @Override
    public void removeNotification(Long notificationId) {
        userNotificationRepository.deleteById(notificationId);
    }
}
