package com.jjangsky.user.application;

import com.jjangsky.user.application.dto.CreateUserRequestDto;
import com.jjangsky.user.application.dto.FollowUserRequestDto;
import com.jjangsky.user.application.interfaces.UserRelationRepository;
import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.repository.FakeUserRelationRepository;
import com.jjangsky.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){
        // when
        userRelationService.follow(requestDto);

        // then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError(){
        //given
        userRelationService.follow(requestDto);

        // when, then
        assertThrows(IllegalArgumentException.class,  () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class,  () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUserFollow_whenUnFollow_thenUserUnFollowSaved(){
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserThrowError(){

        // when, then
        assertThrows(IllegalArgumentException.class,  () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateTwoUser_whenUnFollowSelf_thenUserThrowError(){
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class,  () -> userRelationService.unfollow(sameUser));
    }



}