package com.jjangsky.post.repository;

import com.jjangsky.post.application.interfaces.LikeRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.comment.Comment;
import com.jjangsky.post.repository.entity.comment.CommentEntity;
import com.jjangsky.post.repository.entity.like.LikeEntity;
import com.jjangsky.post.repository.entity.post.PostEntity;
import com.jjangsky.post.repository.jpa.JpaCommentRepository;
import com.jjangsky.post.repository.jpa.JpaLikeRepository;
import com.jjangsky.post.repository.jpa.JpaPostRepository;
import com.jjangsky.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;
    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        /**
         * jpa에서는 save 전에 해당 값이 있는지 확인하기 위해 select 쿼리가 발생하는데
         * 이를 방지하기 위해 insert 쿼리만 실행 되도록 `.persist()`를 사용한다.
         */
//        jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));

    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));

    }

    @Override
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));

    }
}
