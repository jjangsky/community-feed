package com.jjangsky.post.repository.jpa.post_queue;

import com.jjangsky.post.repository.entity.post.PostEntity;
import com.jjangsky.post.repository.entity.post.UserPostQueueEntity;
import com.jjangsky.post.repository.jpa.JpaPostRepository;
import com.jjangsky.post.repository.jpa.JpaUserPostQueueRepository;
import com.jjangsky.user.repository.entity.UserEntity;
import com.jjangsky.user.repository.jpa.JpaUserRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    public void publishPost(PostEntity postEntity) {
        UserEntity authorEntity = postEntity.getAuthor();
        List<Long> followers = jpaUserRelationRepository.findFollowers(authorEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followers.stream()
                .map(userId -> new UserPostQueueEntity(postEntity.getId(), userId, authorEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(postId, userId, targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}