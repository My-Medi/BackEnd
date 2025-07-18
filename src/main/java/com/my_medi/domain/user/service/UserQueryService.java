package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;

import java.util.List;


public interface UserQueryService {

    User getUserById(Long userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User getByKakaoEmail(String email);

    boolean existsByEmail(String email);
}
