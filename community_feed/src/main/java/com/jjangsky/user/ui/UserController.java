package com.jjangsky.user.ui;

import com.jjangsky.common.ui.Response;
import com.jjangsky.user.application.UserService;
import com.jjangsky.user.application.dto.CreateUserRequestDto;
import com.jjangsky.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<User> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return Response.ok(user);
    }
}
