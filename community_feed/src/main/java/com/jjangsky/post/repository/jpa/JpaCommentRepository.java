package com.jjangsky.post.repository.jpa;

import com.jjangsky.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c SET c.content = :#{#comment.getContent()}, c.upDt = now() WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query(value = "UPDATE CommentEntity p SET p.likeCounter = :#{#commentEntity.likeCounter}, p.upDt = now() WHERE p.id = :#{#commentEntity.id}")
    void updateLikeCount(CommentEntity commentEntity);
}
