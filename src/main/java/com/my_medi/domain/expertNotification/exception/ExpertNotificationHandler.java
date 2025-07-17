package com.my_medi.domain.expertNotification.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.user.exception.UserErrorStatus;
import com.my_medi.domain.user.exception.UserHandler;

public class ExpertNotificationHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ExpertNotificationHandler(ExpertNotificationErrorStatus.EXPERT_NOTIFICATION_NOT_FOUND);
    public ExpertNotificationHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
