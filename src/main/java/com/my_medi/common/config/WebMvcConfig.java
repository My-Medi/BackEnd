package com.my_medi.common.config;

import com.my_medi.common.resolver.CustomExpertAuthenticationPrincipalArgumentResolver;
import com.my_medi.common.resolver.CustomUserAuthenticationPrincipalArgumentResolver;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final UserQueryService userQueryService;
    private final ExpertQueryService expertQueryService;

    private final long MAX_AGE_SECS = 3600;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CustomUserAuthenticationPrincipalArgumentResolver(userQueryService));
        argumentResolvers.add(new CustomExpertAuthenticationPrincipalArgumentResolver(expertQueryService));
    }

    //CORS setting
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") //CORS 적용할 URL 패턴
                .allowedOriginPatterns("*") //자원 공유 오리진 지정
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") //요청 허용 메서드
                .allowedHeaders("*") //요청 허용 헤더
                .allowCredentials(true) //요청 허용 쿠키
                .maxAge(MAX_AGE_SECS);
    }
}
