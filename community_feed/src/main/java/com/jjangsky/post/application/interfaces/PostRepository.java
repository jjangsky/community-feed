package com.jjangsky.post.application.interfaces;

import com.jjangsky.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post pos);
    Post findById(Long id);

    Post publish(Post post);
}
