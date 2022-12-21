package com.noose.todo.domain.schedule;

import lombok.Getter;

public class Hashtag {

    @Getter
    private String hashtagName;

    public Hashtag(String hashtagName) {
        if (hashtagName == null) {
            throw new IllegalArgumentException("null 또는 빈 문자열이 될 수 없습니다.");
        }

        this.hashtagName = hashtagName;
    }

    @Override
    public String toString() {
        return hashtagName;
    }
}
