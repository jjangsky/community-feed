package com.jjangsky.post.domain.content;

import com.jjangsky.post.domain.common.DatetimeInfo;

public abstract class Content {

    String contentText;
    DatetimeInfo datetimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updatecontent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDatetime();
    }

    protected abstract void checkText(String contentText);


    public String getContentText() {
        return contentText;
    }


}
