package com.my_medi.common.util;

import static com.my_medi.common.util.PeriodUtil.calculateMonthsBetween;

import java.time.LocalDate;

public class FormUtil {

    public static String formatAge(String birthDate) {
        if (birthDate == null) return "정보 없음";
        return "만 " + BirthDateUtil.getAge(birthDate) + "세";
    }

    public static String formatCareerPeriod(String jobTitle, LocalDate start, LocalDate end) {
        int months = (int) calculateMonthsBetween(start, end);
        return String.format("%s %d개월", jobTitle, months);
    }
}
