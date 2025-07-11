package com.my_medi.domain.user.service;

import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.user.dto.UpdateUserDto;

public interface UserCommandService {

    //TODO [LATER] requestDto에 맞게 argument 변경해주기
    Long registerUser(RegisterMemberDto registerMemberDto);

    //TODO [Now] 변경할 값들 argument로 받아오기 : dto 직접 생성
    Long updateUserInformation(Long userId, UpdateUserDto updateUserDto);

    void deleteUserAccount(Long userId);
}
