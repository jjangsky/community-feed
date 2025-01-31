package com.jjangsky.post.repository.jpa;

import com.jjangsky.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query(value = "UPDATE PostEntity p SET p.likeCounter = :#{#postEntity.likeCounter}, p.upDt = now() WHERE p.id = :#{#postEntity.id}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p SET p.commentCount =  p.commentCount + 1, p.upDt = now() WHERE p.id = :id")
    void increaseCommentCount(Long id);
}
