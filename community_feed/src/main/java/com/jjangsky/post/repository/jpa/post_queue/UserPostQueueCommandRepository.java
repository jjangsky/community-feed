package com.jjangsky.post.repository.jpa.post_queue;

import com.jjangsky.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    /**
     * 내가 팔로우 했을 때 피드로 게시글을 넣어주는 메소드
     */
    void publishPost(PostEntity postEntity);

    /**
     * 유저들을 팔로우 할 때 피드로 게시글을 넣어주는 메소드
     */
    void saveFollowPost(Long userId, Long targetId);

    /**
     * 유저들을 언팔로우 할 때 피드로 게시글을 삭제하는 메소드
     */
    void deleteUnfollowPost(Long userId, Long targetId);
}
