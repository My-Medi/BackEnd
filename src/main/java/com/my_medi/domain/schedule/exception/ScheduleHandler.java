
package com.my_medi.domain.schedule.exception;

import lombok.Getter;

@Getter
public class ScheduleHandler extends RuntimeException {

    private final ScheduleErrorStatus errorStatus;

    public ScheduleHandler(ScheduleErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
