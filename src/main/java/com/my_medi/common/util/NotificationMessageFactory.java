package com.my_medi.common.util;

import com.my_medi.domain.notification.entity.NotificationMessage;

public class NotificationMessageFactory {
    public static String create(NotificationMessage message, Object... args) {
        return String.format(message.getTemplate(), args);
    }
}

