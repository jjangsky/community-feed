package com.jjangsky.user.repository;

import com.jjangsky.user.application.interfaces.UserRelationRepository;
import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.repository.entity.UserEntity;
import com.jjangsky.user.repository.entity.UserRelationEntity;
import com.jjangsky.user.repository.entity.UserRelationIdEntity;
import com.jjangsky.user.repository.jpa.JpaUserRelationRepository;
import com.jjangsky.user.repository.jpa.JpaUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);

        // 삭제의 경우 서비스 레이어에서 변경이 처리가 완료 되어
        // 해당 레이어에서는 변경된 부분을 저장하기만 하면 됨
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
