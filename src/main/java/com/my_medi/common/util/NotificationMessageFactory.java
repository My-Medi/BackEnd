package com.my_medi.common.util;

import com.my_medi.domain.notification.entity.NotificationMessage;

//TODO 사용처 확인해보고 없으면 삭제하기
public class NotificationMessageFactory {
    public static String create(NotificationMessage message, Object... args) {
        return String.format(message.getTemplate(), args);
    }
}

