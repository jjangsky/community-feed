package com.jjangsky.post.application;

import com.jjangsky.post.application.dto.CreateCommentRequestDto;
import com.jjangsky.post.application.dto.LikeRequestDto;
import com.jjangsky.post.application.dto.UpdateCommentRequestDto;
import com.jjangsky.post.application.interfaces.CommentRepository;
import com.jjangsky.post.application.interfaces.LikeRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.comment.Comment;
import com.jjangsky.user.application.UserService;
import com.jjangsky.user.domain.User;

public class CommentService {

    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(LikeRepository likeRepository, CommentRepository commentRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(!likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.unlike();
        likeRepository.unlike(comment, user);
    }


}
