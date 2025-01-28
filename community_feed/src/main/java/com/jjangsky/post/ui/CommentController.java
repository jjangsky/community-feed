package com.jjangsky.post.ui;

import com.jjangsky.common.ui.Response;
import com.jjangsky.post.application.CommentService;
import com.jjangsky.post.application.dto.CreateCommentRequestDto;
import com.jjangsky.post.application.dto.LikeRequestDto;
import com.jjangsky.post.application.dto.UpdateCommentRequestDto;
import com.jjangsky.post.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
