package com.my_medi.api.user.dto;

import com.my_medi.api.member.dto.RegisterMemberDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDto {

    // 회원 공통 정보
    private RegisterMemberDto member;

    // User 정보
    private Float height;
    private Float weight;
}
