package com.my_medi.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
public class UpdateUserDto {

    private String name;
    private String birthDate;
    private String nickname;
    private String phoneNumber;
    private String profileImgUrl;
    private Float height;
    private Float weight;

}
