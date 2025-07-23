package com.my_medi.common.util;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;

public class AnnotationUtil {

    public static <T extends Annotation> T findMethodAnnotation(Class<T> annotationClass, MethodParameter parameter) {
        T annotation = parameter.getParameterAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        Annotation[] annotationsToSearch = parameter.getParameterAnnotations();
        for (Annotation toSearch : annotationsToSearch) {
            annotation = AnnotationUtils.findAnnotation(toSearch.annotationType(), annotationClass);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }
}
