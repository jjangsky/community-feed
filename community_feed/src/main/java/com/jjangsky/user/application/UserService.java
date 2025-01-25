package com.jjangsky.user.application;

import com.jjangsky.user.application.dto.CreateUserRequestDto;
import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.domain.UserInfo;
import org.springframework.stereotype.Service;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
