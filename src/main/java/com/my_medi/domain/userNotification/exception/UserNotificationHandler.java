package com.my_medi.domain.userNotification.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.user.exception.UserErrorStatus;
import com.my_medi.domain.user.exception.UserHandler;

public class UserNotificationHandler extends GeneralException {
    public static final GeneralException NOT_FOUND
            = new UserNotificationHandler(UserNotificationErrorStatus.USER_NOTIFICATION_NOT_FOUND);
    public UserNotificationHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
