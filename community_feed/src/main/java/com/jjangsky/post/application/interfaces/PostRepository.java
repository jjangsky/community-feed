package com.jjangsky.post.application.interfaces;

import com.jjangsky.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post pos);
    Optional<Post> findById(Long id);
}
