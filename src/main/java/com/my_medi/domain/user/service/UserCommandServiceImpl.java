package com.my_medi.domain.user.service;


import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.user.dto.RegisterUserDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.member.entity.Role;
import com.my_medi.domain.user.dto.UpdateUserDto;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.user.exception.UserHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    public Long registerUser(RegisterUserDto registerUserDto){
        // 공통 회원정보
        RegisterMemberDto m = registerUserDto.getMember();

        //TODO [LATER] requestDto에 맞게 구현예정
        User user = User.builder()
                .name(m.getName())
                .birthDate(m.getBirthDate())
                .gender(m.getGender())
                .username(UUID.randomUUID().toString())
                .email(m.getEmail())
                .phoneNumber(m.getPhoneNumber())
                .profileImgUrl(m.getProfileImgUrl())
                .role(Role.USER)
                .build();
        return userRepository.save(user).getId();

    }


    @Override
    public Long updateUserInformation(Long userId, UpdateUserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        user.modifyUserInfo(dto);
        return user.getId();
    }

    @Override
    public void deleteUserAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        userRepository.delete(user); // Hard delete
    }

}

