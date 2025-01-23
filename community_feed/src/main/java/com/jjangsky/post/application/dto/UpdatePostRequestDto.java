package com.jjangsky.post.application.dto;

public record UpdatePostRequestDto(Long postId, Long userId, String content, String state) {
}
