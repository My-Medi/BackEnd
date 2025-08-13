package com.my_medi.common.util;

import com.my_medi.common.interfaces.KeyedEnum;
import com.my_medi.domain.expert.entity.Specialty;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class EnumConvertUtil {

    public static <E extends Enum<E> & KeyedEnum> E convert(Class<E> enumClass, String key) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Key not found in " + enumClass.getSimpleName()));
    }

    public static <E extends Enum<E> & KeyedEnum> E convertOrNull(Class<E> enumClass, String key) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElse(null);
    }

    public static Specialty getRandomSpecialty() {
        Specialty[] values = Specialty.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(values.length);
        return values[randomIndex];
    }
}
