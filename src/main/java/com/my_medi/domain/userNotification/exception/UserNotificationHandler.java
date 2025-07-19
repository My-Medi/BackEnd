package com.my_medi.domain.userNotification.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.user.exception.UserErrorStatus;
import com.my_medi.domain.user.exception.UserHandler;

public class UserNotificationHandler extends GeneralException {
    public static final GeneralException NOT_FOUND
            = new UserNotificationHandler(UserNotificationErrorStatus.USER_NOTIFICATION_NOT_FOUND);
    /**
     * Constructs a new UserNotificationHandler with the specified error code.
     *
     * @param baseErrorCode the error code representing the specific user notification error
     */
    public UserNotificationHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
