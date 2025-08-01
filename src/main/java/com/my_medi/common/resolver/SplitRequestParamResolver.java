package com.my_medi.common.resolver;

import com.my_medi.common.annotation.RequestParamList;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;

@Component
public class SplitRequestParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // `@SplitRequestParam`이 적용된 List<String> 파라미터인지 확인
        return parameter.hasParameterAnnotation(RequestParamList.class)
                && parameter.getParameterType().equals(List.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 어노테이션 정보 가져오기
        RequestParamList annotation = parameter.getParameterAnnotation(RequestParamList.class);
        if (annotation == null) {
            return null;
        }

        // HTTP 요청 파라미터 가져오기
        String paramName = annotation.value();
        String rawValue = webRequest.getParameter(paramName);

        // 값이 없으면 빈 리스트 반환
        if (rawValue == null) {
            return annotation.nullable() ? null : List.of();
        }

        // 지정된 delimiter로 문자열을 분할하여 리스트로 변환
        return Arrays.asList(rawValue.split(annotation.delimiter()));
    }
}
