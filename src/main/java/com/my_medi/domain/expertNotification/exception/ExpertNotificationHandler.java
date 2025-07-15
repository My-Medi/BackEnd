package com.my_medi.domain.expertNotification.exception;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;

public class ExpertNotificationHandler extends GeneralException {
    public ExpertNotificationHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
