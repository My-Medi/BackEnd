package com.my_medi.domain.report.exception;

import com.my_medi.common.exception.BaseErrorCode;
import com.my_medi.common.exception.GeneralException;

public class ReportHandler extends GeneralException {
    public static final GeneralException NOT_FOUND
            = new ReportHandler(ReportErrorStatus.REPORT_NOT_FOUND);
    public ReportHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
