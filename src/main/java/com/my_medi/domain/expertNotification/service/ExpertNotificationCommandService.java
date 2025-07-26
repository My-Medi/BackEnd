package com.my_medi.domain.expertNotification.service;

public interface ExpertNotificationCommandService {

    //TODO [Now] entity에 추가적으로 필요한 내용들 dto로 만들어서 argument에 추가하기
    void sendNotificationToExpert(Long expertId, Long sourceId);

    void removeNotification(Long notificationId);
}
