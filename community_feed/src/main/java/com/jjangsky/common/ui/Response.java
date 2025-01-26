package com.jjangsky.common.ui;

import com.jjangsky.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "OK", value);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
