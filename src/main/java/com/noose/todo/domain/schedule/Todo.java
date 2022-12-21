package com.noose.todo.domain.schedule;

public class Todo {

    private static final int MAX_LENGTH = 30;

    private String contents;
    private Status status;

    public Todo(String contents) {
        if (contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("글자수는 빈값이거나 null 일 수 없습니다.");
        }

        if (contents.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH + " 글자를 초과할 수 없습니다.");
        }

        this.contents = contents;
        this.status = Status.INCOMPLETE;
    }

    public void complete() {
        this.status = Status.COMPLETE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }
}
