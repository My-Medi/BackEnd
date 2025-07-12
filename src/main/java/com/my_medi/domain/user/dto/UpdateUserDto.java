package com.my_medi.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class UpdateUserDto {

    private String name;
    private LocalDate birthDate;
    private UUID username;
    private String nickname;
    private String phoneNumber;
    private String profileImgUrl;
    private Float height;
    private Float weight;

}
