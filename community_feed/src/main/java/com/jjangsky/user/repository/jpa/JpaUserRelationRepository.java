package com.jjangsky.user.repository.jpa;

import com.jjangsky.user.repository.entity.UserRelationEntity;
import com.jjangsky.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    List<Long> findFollowers(Long id);
}
