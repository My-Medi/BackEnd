package com.my_medi.domain.user.service;


import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.domain.user.dto.UpdateUserDto;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.user.exception.UserHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    public Long registerUser(RegisterMemberDto registerMemberDto){
        //TODO [LATER] requestDto에 맞게 구현예정
        return null;
    }


    @Override
    public Long updateUserInformation(Long userId, UpdateUserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
        user.modifyInfo(dto);
        return user.getId();
    }

    @Override
    public void deleteUserAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND);

        userRepository.delete(user); // Hard delete
    }

}

