package com.my_medi.domain.userNotification.exception;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;

public class UserNotificationHandler extends GeneralException {
    public UserNotificationHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
