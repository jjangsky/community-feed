package com.jjangsky.user.domain.post.domain;

import com.jjangsky.user.domain.User;

public class Post {

    private final User author;
    private final PostContent content;

    public Post(User author, PostContent content) {
        if(author == null){
            throw new IllegalArgumentException();
        }

        this.author = author;
        this.content = content;
    }
}
