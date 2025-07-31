package com.my_medi.security.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginRequestDto {

    private String loginId;
    private String password;
}
