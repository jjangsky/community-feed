package com.jjangsky.post.application.interfaces;

import com.jjangsky.post.domain.Post;
import com.jjangsky.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);
}
