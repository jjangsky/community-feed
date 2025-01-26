package com.jjangsky.user.application;

import com.jjangsky.user.application.dto.FollowUserRequestDto;
import com.jjangsky.user.application.interfaces.UserRelationRepository;
import com.jjangsky.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targerUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targerUser)){
            throw new IllegalArgumentException();
        }

        user.follow(targerUser);
        userRelationRepository.save(user, targerUser);
    }

    public void unfollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targerUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targerUser)){
            throw new IllegalArgumentException();
        }

        user.unfollow(targerUser);
        userRelationRepository.delete(user, targerUser);
    }
}
