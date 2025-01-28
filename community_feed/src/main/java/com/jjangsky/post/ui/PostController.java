package com.jjangsky.post.ui;

import com.jjangsky.common.ui.Response;
import com.jjangsky.post.application.PostService;
import com.jjangsky.post.application.dto.CreatePostRequestDto;
import com.jjangsky.post.application.dto.UpdatePostRequestDto;
import com.jjangsky.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto dto) {
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }
}
