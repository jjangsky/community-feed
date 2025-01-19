package com.jjangsky.user.domain;

import com.jjangsky.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo info) {
        if(info == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = info;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        /**
         *  팔로우 행위가 일어날 경우 본인 팔로잉 +1
         *  팔로우 한 사람 입장에서는 팔로워 +1
         */
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followingCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
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
