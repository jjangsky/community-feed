package com.jjangsky.post.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;

public class DatetimeInfo {

    private boolean isEdited;
    @Getter
    private LocalDateTime dateTime;

    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }
}
