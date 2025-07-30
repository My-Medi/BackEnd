package com.my_medi.domain.userNotification.util;

import com.my_medi.domain.userNotification.entity.NotificationMessage;

public class NotificationMessageFactory {
    public static String create(NotificationMessage message, Object... args) {
        return String.format(message.getTemplate(), args);
    }
}

