package com.noose.todo.domain.schedule;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Body {

    private static final int MAX_LENGTH = 500;

    private String contents;

    public Body(String contents) {
        validate(contents);

        this.contents = contents;
    }

    private void validate(String contents) {
        if (contents == null) {
            throw new IllegalArgumentException("본문 내용은 null 일 수 없습니다.");
        }

        if (contents.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("본문 내용 길이는" + MAX_LENGTH + "보다 클 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }
}
