package com.jjangsky.post.application.dto;

import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
