package com.jjangsky.user.repository.jpa;

import com.jjangsky.user.repository.entity.UserRelationEntity;
import com.jjangsky.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    void deleteById(UserRelationEntity id);
}
