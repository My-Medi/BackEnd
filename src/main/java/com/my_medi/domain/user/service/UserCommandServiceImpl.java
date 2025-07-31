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
        //TODO [LATER] requestDto에 맞게 구현예정
        User user = User.builder()
                .name(registerUserDto.getMember().getName())
                .birthDate(registerUserDto.getMember().getBirthDate())
                .gender(registerUserDto.getMember().getGender())
                .username(UUID.randomUUID().toString())
                .email(registerUserDto.getMember().getEmail())
                .phoneNumber(registerUserDto.getMember().getPhoneNumber())
                .profileImgUrl(registerUserDto.getMember().getProfileImgUrl())
                .role(Role.USER)
                .height(registerUserDto.getHeight())
                .weight(registerUserDto.getWeight())
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
    public Long deleteUserAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        userRepository.delete(user); // TODO : Hard delete이나 추후 soft로 변경예정
        return user.getId();
    }

}

