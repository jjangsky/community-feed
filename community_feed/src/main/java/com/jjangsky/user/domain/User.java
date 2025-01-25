package com.jjangsky.user.domain;

import com.jjangsky.common.domain.PositiveIntegerCounter;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private  Long id;
    private  UserInfo info;
    private  PositiveIntegerCounter followingCount;
    private  PositiveIntegerCounter followerCounter;

    public UserInfo getInfo() {
        return info;
    }

    public Long getId() {
        return id;
    }

    public int followerCount() {
        return followerCounter.getCount();
    }

    public int followingCount() {
        return followingCount.getCount();
    }

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

    public String getProfileImage() {
        return info.getProfileImageUrl();
    }

    public String getName() {
        return info.getName();
    }

    private void increaseFollowerCount() {
        followerCounter.increase();
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
