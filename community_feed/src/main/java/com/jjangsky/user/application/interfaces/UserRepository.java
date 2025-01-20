package com.jjangsky.user.application.interfaces;

import com.jjangsky.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
}
