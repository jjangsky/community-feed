package com.jjangsky.user.repository.entity;

import com.jjangsky.common.domain.PositiveIntegerCounter;
import com.jjangsky.common.repository.entity.TimeBaseEntity;
import com.jjangsky.user.domain.User;
import com.jjangsky.user.domain.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "community_user")
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    /**
     * OneToMany 에서는 지연 로딩이 default 값으로 설정되어
     * 해당 필드가 사용될 때 까지 데이터를 가져오지 않는다.
     *
     * 하지만, 데이터가 많아질 수록 성능에 영향을 줄 수 있기 때문에
     *      -> 해당 데이터를 다 자바 객체로 만들어 놓기 때문에
     * 실무에서는 잘 사용하지 않는다.
     */
//    @OneToMany
//    private List<PostEntity> posts;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImage))
                .followerCounter(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
