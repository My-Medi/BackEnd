package com.my_medi.common.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BirthDateUtil {

    public static int getAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalStateException("birthDate is not set");
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static List<Integer> getAgeGroup5yrRange(int age) {
        int groupBase = (age / 10) * 2 + 1;
        return List.of(groupBase, groupBase + 1);
    }

    public static int getAgeGroup10yr(int age) {
        return (age / 10) * 10;
    }
}
