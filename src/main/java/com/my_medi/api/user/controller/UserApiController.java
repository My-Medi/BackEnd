package com.my_medi.api.user.controller;

import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {

    //지금은 테스트를 위해 레포지토리 의존성 주입을 해둔 상태입니다
    private final UserRepository userRepository;

    //TODO remove this method
    @Transactional
    @PostMapping
    public Long registerUserForTest() {
        return userRepository.save(User
                .builder()
                .userUuid("test")
                .build()).getId();
    }
}
