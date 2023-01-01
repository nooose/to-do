package com.noose.todo.domain.note;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Title {

    private static final int MAX_LENGTH = 20;

    @Column(name = "title")
    private String value;

    public Title(String value) {
        validate(value);

        this.value = value.trim();
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
