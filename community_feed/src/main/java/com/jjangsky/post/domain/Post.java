package com.jjangsky.post.domain;

import com.jjangsky.common.domain.PositiveIntegerCounter;
import com.jjangsky.post.domain.content.Content;
import com.jjangsky.post.domain.content.PostContent;
import com.jjangsky.post.domain.content.PostPublicationState;
import com.jjangsky.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private final Long id;
    private final User author;
    @Getter
    private final Content content;

    private PostPublicationState state;
    private final PositiveIntegerCounter likeCount;

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    @Builder
    public Post(Long id, User author, Content content, PostPublicationState state, PositiveIntegerCounter positiveIntegerCounter) {
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.state = state;
        this.likeCount = positiveIntegerCounter;
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

    public String getContentText() {
        return content.getContentText();
    }




}
