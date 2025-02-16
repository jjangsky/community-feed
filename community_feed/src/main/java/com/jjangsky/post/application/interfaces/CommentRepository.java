package com.jjangsky.post.application.interfaces;

import com.jjangsky.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);

}
