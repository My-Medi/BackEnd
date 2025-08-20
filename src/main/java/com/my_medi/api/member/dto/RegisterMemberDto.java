package com.my_medi.api.member.dto;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegisterMemberDto {
    @NotNull(message = "성명을 입력하세요.")
    private String name;

    @NotBlank(message = "생년월일은 필수입니다.")
    @Pattern(
            regexp = "^(\\d{6})$", //숫자 6자리 고정
            message = "생년월일은 yymmdd 형식의 6자리 숫자여야 합니다. (예: 970101)"
    )
    @Schema(example = "000102")
    private String birthDate;

    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Schema(example = "01012345678")
    @Pattern(
            regexp = "^(01[016789])[-]?\\d{3,4}[-]?\\d{4}$",     // 010-1234-5678 또는 01012345678 허용
            message = "전화번호 형식이 올바르지 않습니다."
    )
    private String phoneNumber;

    private String profileImgUrl;

    private String loginId;

    private String password;


}
