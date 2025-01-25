package com.jjangsky.post.domain;

import com.jjangsky.common.domain.PositiveIntegerCounter;
import com.jjangsky.post.domain.content.Content;
import com.jjangsky.post.domain.content.PostContent;
import com.jjangsky.post.domain.content.PostPublicationState;
import com.jjangsky.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    private PostPublicationState state;

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like (User user ) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }


    public void unlike () {
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if(!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(updateContent);
    }

    public Content getContent() {
        return content;
    }

    public void updateContent(User user, String content, PostPublicationState state) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("only author can update content");
        }

        if (state == null) {
            state = PostPublicationState.PUBLIC;
        }

        this.content.updateContent(content);
        this.state = state;
    }

    public void updateState(PostPublicationState state) {
        this.state = state;
    }

    public PostPublicationState getState() {
        return state;
    }


}
