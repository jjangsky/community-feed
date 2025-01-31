package com.jjangsky.post.repository;

import com.jjangsky.post.application.interfaces.CommentRepository;
import com.jjangsky.post.application.interfaces.PostRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.comment.Comment;
import com.jjangsky.post.repository.entity.comment.CommentEntity;
import com.jjangsky.post.repository.jpa.JpaCommentRepository;
import com.jjangsky.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);
        if(comment.getId() != null){
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        return commentEntity.toComment();
    }
}
