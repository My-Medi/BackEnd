
package com.my_medi.domain.schedule.exception;


import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ScheduleHandler extends GeneralException {

    public static final GeneralException NOT_FOUND =
            new ScheduleHandler(ScheduleErrorStatus.SCHEDULE_NOT_FOUND);

    public static final GeneralException MISMATCHED_CONSULTATION =
            new ScheduleHandler(ScheduleErrorStatus.NOT_MATCHED_CONSULTATION);

    public ScheduleHandler(BaseErrorCode code) {
        super(code);
    }
}
