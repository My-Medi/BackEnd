package com.my_medi.domain.user.service;

import com.my_medi.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUserUuid(String uuid);

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);
}
