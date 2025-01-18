package com.jjangsky.post.domain.content;

public abstract class Content {

    String contentText;

    protected Content(String contentText) {
        this.contentText = contentText;
    }

    protected abstract void checkText(String contentText);


    public String getContentText() {
        return contentText;
    }


}
