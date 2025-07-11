package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long expertId) {
        return userRepository.findById(expertId)
                .orElseThrow(() -> UserHandler.NOT_FOUND); //UserHandler, UserErrorState 사용
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
