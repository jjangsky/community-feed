package com.jjangsky.post.domain;

import com.jjangsky.common.domain.PositiveIntegerCounter;
import com.jjangsky.post.domain.content.PostContent;
import com.jjangsky.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;

    public Post(Long id, User author, PostContent content) {
        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like (User user ) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public void unlike () {
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent) {
        if(!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updatecontent(updateContent);
    }


}
