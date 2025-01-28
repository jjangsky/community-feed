package com.jjangsky.post.repository;

import com.jjangsky.post.application.interfaces.PostRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.repository.entity.post.PostEntity;
import com.jjangsky.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    @Override
    public Post save(Post pos) {
        PostEntity postEntity = new PostEntity();
        postEntity = jpaPostRepository.save(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity =  jpaPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return postEntity.toPost();
    }

    @Override
    public Post publish(Post post) {
        return null;
    }
}
