package com.my_medi.domain.userNotification.service;

import com.my_medi.api.UserNotification.dto.SendNotificationToUserDto;

public interface UserNotificationCommandService {

    //TODO [Now] entity에 추가적으로 필요한 내용들 dto로 만들어서 argument에 추가하기
    Long sendNotificationToUser(Long userId, Long sourceId, SendNotificationToUserDto sendNotificationToUserDto);

    void removeNotification(Long notificationId);
}
