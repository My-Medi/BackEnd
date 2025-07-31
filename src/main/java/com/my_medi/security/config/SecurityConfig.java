package com.my_medi.security.config;

import com.my_medi.security.exception.JwtAccessDeniedHandler;
import com.my_medi.security.exception.JwtAuthenticationEntryPoint;
import com.my_medi.security.filter.JwtAuthenticationFilter;
import com.my_medi.security.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.http.HttpMethod;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        configureCorsAndSecurity(httpSecurity);
        configureAuth(httpSecurity);
//        configureOAuth2(httpSecurity);
        configureExceptionHandling(httpSecurity);
        addFilter(httpSecurity);

        return httpSecurity.build();
    }

    private static void configureCorsAndSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers(
                        httpSecurityHeadersConfigurer ->
                                httpSecurityHeadersConfigurer.frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::disable
                                )
                )
                // stateless한 rest api 이므로 csrf 공격 옵션 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                //.formLogin(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                );
    }

    private void configureAuth(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequest -> {
                    authorizeRequest
                            .requestMatchers("/ws/**", "/subscribe/**", "/publish/**").permitAll()
                            .requestMatchers("/", "/.well-known/**", "/css/**", "/*.ico", "/error", "/images/**").permitAll()
                            .requestMatchers("/api/login", "/api/signup", "/api/health").permitAll()
                            .requestMatchers(HttpMethod.GET, permitAllGetPaths()).permitAll() // [GET] 인증 없이 접근 가능한 공개 API 경로
                            .requestMatchers(HttpMethod.POST, permitAllPostPaths()).permitAll() // [POST] 인증 없이 접근 가능한 공개 API 경로
                            .requestMatchers(swaggerPermitAllPaths()).permitAll()
                            .requestMatchers(authPermitAllPaths()).permitAll()
//                            .requestMatchers(permitAllRequestV2()).permitAll()
                            .anyRequest().authenticated();  // 그 외 모든 요청은 인증 필요
                });
    }

    //[GET] 인증 없이 접근 허용할 경로 목록
    private String[] permitAllGetPaths() {
        return new String[]{

                "/api/v1/test",
                "/api/v1/test/health-checkup",
                "/api/v1/examples/user",
                "/api/v1/examples/global"
        };
    }

    //[POST] 인증 없이 접근 허용할 경로 목록
    private String[] permitAllPostPaths() {
        return new String[]{
                "/api/v1/tokens/login",
                "/api/v1/tokens/reissue",
                "/api/v1/users",
                "/api/v1/experts",
                "/api/v1/images"
        };
    }


    private void addFilter(HttpSecurity httpSecurity) {
        httpSecurity
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);
    }

    private void configureExceptionHandling(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler));        // 403
    }

    private String[] swaggerPermitAllPaths() {
        return new String[]{
                "/swagger-ui/**",
                "/swagger-ui",
                "/swagger-ui.html",
                "/swagger/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/profile"
        };
    }

    private String[] authPermitAllPaths() {
        return new String[]{
                "/oauth2/**",
                "/login/**",
                "/auth/**"
        };
    }
}
