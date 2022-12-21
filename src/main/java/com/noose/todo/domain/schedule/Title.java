package com.noose.todo.domain.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Title {

    private static final int MAX_LENGTH = 20;

    private String value;

    public Title(String value) {
        validate(value);

        this.value = value;
    }

    private void validate(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("null 또는 빈 문자열이 될 수 없습니다.");
        }

        if (title.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("제목은 20 글자를 초과할 수 없습니다. 입력한 길이: " + title.length());
        }
    }
}
