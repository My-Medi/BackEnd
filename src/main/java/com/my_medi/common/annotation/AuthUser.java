package com.my_medi.common.annotation;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(hidden=true)
@Documented
public @interface AuthUser {
    boolean errorOnInvalidType() default true;
}
