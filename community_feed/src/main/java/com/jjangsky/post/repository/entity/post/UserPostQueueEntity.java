package com.jjangsky.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="community_user_post_queue")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPostQueueEntity {

    /**
     * 팔로우 한 사람에 대해서 게시글을 확인할 수 있는 Entity 객체
     * 작성자(authorId)를 팔로우 한 사람에 대해서 게시글을 조회할 수 있음
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long postId;
    private Long authorId;

    public UserPostQueueEntity(Long postId, Long userId, Long authorId) {
        this.postId = postId;
        this.userId = userId;
        this.authorId = authorId;
    }
}