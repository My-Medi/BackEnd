package com.my_medi.domain.userNotification.service;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.userNotification.entity.UserNotification;
import com.my_medi.domain.userNotification.exception.UserNotificationHandler;
import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNotificationQueryServiceImpl implements UserNotificationQueryService {
    private final UserNotificationRepository userNotificationRepository;

    /**
     * Retrieves all user notifications associated with the specified user ID.
     *
     * @param userId the unique identifier of the user whose notifications are to be fetched
     * @return a list of UserNotification entities for the given user ID
     */
    @Override
    public List<UserNotification> getNotificationByUserId(Long userId) {
        return userNotificationRepository.findByUserId(userId);
    }

    /**
     * Retrieves a user notification by its unique notification ID.
     *
     * @param notificationId the ID of the notification to retrieve
     * @return the UserNotification entity with the specified ID
     * @throws UserNotificationHandler.NOT_FOUND if no notification is found with the given ID
     */
    @Override
    public UserNotification getNotificationById(Long notificationId) {
        return userNotificationRepository.findById(notificationId)
                .orElseThrow(() -> UserNotificationHandler.NOT_FOUND);
    }
}
