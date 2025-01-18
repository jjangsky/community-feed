package com.jjangsky.user.domain;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;

    public User(Long id, String name, UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
    }

    /**
     * 사용자에 대해서 해쉬코드 값 비교가 아닌 단순 Id 값으로 비교 예정
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
