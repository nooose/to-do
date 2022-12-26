package com.noose.todo.dto.response;

public record Response<T>(
    String statusCode,
    T result
) {

    public static <T> Response<T> ok(T t) {
        return new Response<>("OK", t);
    }

    public static <T> Response<T> error(String responseCode, T t) {
        return new Response<>(responseCode, t);
    }
}
