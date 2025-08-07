package com.my_medi.common.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeriodUtil {
    public static long calculateMonthsBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return 0L;
        return ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));
    }
}
