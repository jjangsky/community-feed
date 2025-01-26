package com.jjangsky.post.repository.entity.like;


import com.jjangsky.common.repository.entity.TimeBaseEntity;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.comment.Comment;
import com.jjangsky.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    /**
     * Like에 대해서 게시글의 대한 Like와 댓글에 대한 Like를 하나의 테이블에서
     * 관리할 수 있음
     */

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeedUser) {
        this.id = new LikeIdEntity(post.getId(), likeedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeedUser) {
        this.id = new LikeIdEntity(comment.getId(), likeedUser.getId(), LikeTarget.COMMENT.name());
    }
}