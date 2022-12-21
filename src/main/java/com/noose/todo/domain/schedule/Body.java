package com.noose.todo.domain.schedule;

public class Body {

    private static final int MAX_LENGTH = 500;

    private String value;

    public Body(String value) {
        validate(value);

        this.value = value;
    }

    private void validate(String body) {
        if (body == null || body.equals("")) {
            throw new IllegalArgumentException("글자수는 빈값이거나 null 일 수 없습니다.");
        }

        if (body.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("본문 내용 길이는" + MAX_LENGTH + "보다 클 수 없습니다.");
        }
    }
}
