package com.my_medi.security.jwt.service;

import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.member.entity.Member;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.service.UserQueryService;
import com.my_medi.security.exception.JwtAuthenticationException;
import com.my_medi.security.exception.SecurityErrorStatus;
import com.my_medi.security.jwt.dto.JwtToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.my_medi.security.jwt.service.TokenService;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    private final UserQueryService userQueryService;
    private final ExpertQueryService expertQueryService;
    private final Key key;

    public TokenServiceImpl(@Value("${app.jwt.secret}") String secretKey,
                            UserQueryService userQueryService,
                            ExpertQueryService expertQueryService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userQueryService = userQueryService;
        this.expertQueryService = expertQueryService;
    }

    @Override
    public JwtToken login(String kakaoEmail) {
        Member member;

        if (userQueryService.existsByEmail(kakaoEmail)) {
            member = userQueryService.getByKakaoEmail(kakaoEmail);
        } else if (expertQueryService.existsByEmail(kakaoEmail)) {
            member = expertQueryService.getByKakaoEmail(kakaoEmail);
        } else {
            throw new JwtAuthenticationException(SecurityErrorStatus.AUTH_INVALID_TOKEN);
        }

        String role = member.getRole().name(); // 예: ROLE_USER, ROLE_EXPERT

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                kakaoEmail,
                "",
                List.of(new SimpleGrantedAuthority(role))
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        return generateToken(authentication);
    }


    @Override
    public JwtToken generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + StaticVariable.ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now + StaticVariable.REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpire(accessTokenExpiresIn)
                .refreshTokenExpire(refreshTokenExpiresIn)
                .build();
    }

    @Override
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        if (claims.get("auth") == null) {
            throw new JwtAuthenticationException(SecurityErrorStatus.AUTH_INVALID_TOKEN);
        }

        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new org.springframework.security.core.userdetails.User(
                claims.getSubject(), "", authorities
        );

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException(SecurityErrorStatus.AUTH_TOKEN_HAS_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthenticationException(SecurityErrorStatus.AUTH_TOKEN_IS_UNSUPPORTED);
        } catch (MalformedJwtException | SecurityException | IllegalArgumentException e) {
            throw new JwtAuthenticationException(SecurityErrorStatus.AUTH_INVALID_TOKEN);
        }
    }

    @Override
    public JwtToken issueTokens(String refreshToken) {
        throw new UnsupportedOperationException("Redis 미사용 상태에서는 재발급을 지원하지 않습니다.");
    }

    @Override
    public boolean logout(String refreshToken) {
        return true;
    }

    @Override
    public boolean existsRefreshToken(String refreshToken) {
        return false;
    }

    @Override
    public Date parseExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
