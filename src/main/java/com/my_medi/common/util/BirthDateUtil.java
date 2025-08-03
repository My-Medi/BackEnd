package com.my_medi.common.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BirthDateUtil {

    public static int getAge(String birthDate) {
        if (birthDate == null) {
            throw new IllegalStateException("birthDate is not set");
        }
        LocalDate parsedDate;
        if (birthDate.length() == 6) {
            // "000926" 형식: yyMMdd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            parsedDate = LocalDate.parse(birthDate, formatter);

            int year = parsedDate.getYear();
            if (year > LocalDate.now().getYear()) {
                parsedDate = parsedDate.minusYears(100);
            }
        } else {
        throw new IllegalArgumentException("생년월일 형식이 잘못되었습니다.");
    }

        return Period.between(parsedDate, LocalDate.now()).getYears();
    }

    public static List<Integer> getAgeGroup5yrRange(int age) {
        int groupBase = (age / 10) * 2 + 1;
        return List.of(groupBase, groupBase + 1);
    }

    public static int getAgeGroup10yr(int age) {
        return (age / 10) * 10;
    }
}
