package com.my_medi.domain.userNotification.service;

public interface UserNotificationCommandService {

    //TODO [Now] entity에 추가적으로 필요한 내용들 dto로 만들어서 argument에 추가하기
    void sendConsultationRequestApproveNotificationToUser(Long userId, Long consultationId);

    void sendScheduleNotificationToUser(Long userId, Long scheduleId);

    void removeNotification(Long notificationId);
}
