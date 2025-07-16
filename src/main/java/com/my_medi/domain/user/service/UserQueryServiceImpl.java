package com.my_medi.domain.user.service;

import com.my_medi.common.exception.GeneralException;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertErrorStatus;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserErrorStatus;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //queryservice에는 readonly
public class UserQueryServiceImpl implements UserQueryService{
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserHandler.NOT_FOUND); //UserHandler, UserErrorState 사용
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
    }

    @Override
    public User getByKakaoEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserHandler.NOT_FOUND);
    }
}
