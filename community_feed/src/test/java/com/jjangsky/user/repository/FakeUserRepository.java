package com.jjangsky.user.repository;

import com.jjangsky.user.application.interfaces.UserRepository;
import com.jjangsky.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    /**
     * FakeRepository를 만들어서 실제 RDB에서 데이터가 저장되고
     * 반환 되는 것처럼 구성할 수 있다.
     */
    private final Map<Long, User> store = new HashMap<>();
    @Override
    public User save(User user) {
        if(user.getId() != null){
            store.put(user.getId(), user);
        }
        Long id = store.size() + 1L;
        User newUser = new User(id, user.getInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
