package com.jjangsky.user.repository;

import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.repository.entity.UserEntity;
import com.jjangsky.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    /**
     * 실행할 메소드 자체를 UserRepository인터페이스에 정의하고
     * 구현체에서 해당 메소드를 실행하는 방식으로 JPA에 의존적이지 않게 만들 수 있음
     *
     * -> MyBatis를 사용하거나 다른 ORM을 사용할 때도
     * UserRepository 인터페이스를 그대로 사용할 수 있음
     */

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}
