package com.my_medi.domain.user.service;

import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.user.dto.UpdateUserDto;

public interface UserCommandService {

    Long registerUser(RegisterMemberDto registerMemberDto);

    Long updateUserInformation(Long userId, UpdateUserDto updateUserDto);

    Long deleteUserAccount(Long userId);
}
