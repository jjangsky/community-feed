package com.jjangsky.user.application;

import com.jjangsky.user.application.dto.CreateUserRequestDto;
import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.domain.UserInfo;
import com.jjangsky.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    /**
     * 문제 발생
     * Service는 Repository와 관계를 맺는데 테스트 코드에서는 구현할 수 없음
     * Mock 또는 Fake 객체를 사용해서 테스트 진행
     */
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("test" , "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals(userInfo.getName(),"test");

    }

}