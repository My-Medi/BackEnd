package com.my_medi.domain.notification.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ExpertNotificationHandler extends GeneralException {

    public static final GeneralException NOT_FOUND
            = new ExpertNotificationHandler(ExpertNotificationErrorStatus.EXPERT_NOTIFICATION_NOT_FOUND);
    public ExpertNotificationHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
