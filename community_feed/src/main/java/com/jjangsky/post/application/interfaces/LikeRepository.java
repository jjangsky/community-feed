package com.jjangsky.post.application.interfaces;

import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.comment.Comment;
import com.jjangsky.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);
}
