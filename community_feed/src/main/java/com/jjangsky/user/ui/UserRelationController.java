package com.jjangsky.user.ui;

import com.jjangsky.common.ui.Response;
import com.jjangsky.user.application.UserRelationService;
import com.jjangsky.user.application.dto.FollowUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> follow(@RequestBody FollowUserRequestDto dto) {
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollow(@RequestBody FollowUserRequestDto dto) {
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }

}
