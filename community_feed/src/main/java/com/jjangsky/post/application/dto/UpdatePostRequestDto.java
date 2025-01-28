package com.jjangsky.post.application.dto;

import com.jjangsky.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
