package com.my_medi.common.resolver;

import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.service.UserQueryService;
import com.my_medi.security.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

import static com.my_medi.common.util.AnnotationUtil.findMethodAnnotation;

@Slf4j
@RequiredArgsConstructor
public class CustomExpertAuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    private final ExpertQueryService expertQueryService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return findMethodAnnotation(AuthExpert.class, parameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        AuthExpert annotation = findMethodAnnotation(AuthExpert.class, parameter);
        if (principal == "anonymousUser") {
            throw JwtAuthenticationException.UNAUTHORIZED_LOGIN_DATA;
        }
        findMethodAnnotation(AuthExpert.class, parameter);
        Expert expert = expertQueryService.getExpertByUsername(authentication.getName());

        if (principal != null && !ClassUtils.isAssignable(parameter.getParameterType(), expert.getClass())) {
            if (annotation.errorOnInvalidType()) {
                throw JwtAuthenticationException.ASSIGNABLE_PARAMETER;
            }
        }

        return expert;
    }


}
