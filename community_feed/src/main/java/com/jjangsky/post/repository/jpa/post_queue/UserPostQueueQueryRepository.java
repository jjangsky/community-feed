package com.jjangsky.post.repository.jpa.post_queue;

import com.jjangsky.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostlist(Long userId, Long lastPostId);
}
