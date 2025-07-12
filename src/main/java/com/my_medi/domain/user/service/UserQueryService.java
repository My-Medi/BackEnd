package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserQueryService {

    User getUserById(Long userId);
    User getUserByUsername(UUID username);
    List<User> getAllUsers();
}
