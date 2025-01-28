package com.jjangsky.post.repository.jpa;

import com.jjangsky.post.repository.entity.like.LikeEntity;
import com.jjangsky.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
