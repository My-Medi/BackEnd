package com.my_medi.domain.schedule.exception;


import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;

public class ScheduleHandler extends GeneralException {
    public ScheduleHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}