package com.jjangsky.post.domain;

import com.jjangsky.post.domain.content.Content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        // 검증 로직은 재정의함 
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if(contentText.length() > MAX_POST_LENGTH){
            throw new IllegalArgumentException();
        }

        if(contentText.length() < MIN_POST_LENGTH){
            throw new IllegalArgumentException();
        }
    }





    /*
    다형성을 적용하여 추상화
    private final String content;

    public PostContent(String content) {

        if(content == null || content.length() < 5 || content.length() > 500){
            throw new IllegalArgumentException();
        }
        this.content = content;
    }

    public String getContent() {
        return content;
    }*/
}
