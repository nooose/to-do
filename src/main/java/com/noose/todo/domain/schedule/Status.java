package com.noose.todo.domain.schedule;

public enum Status {
    COMPLETE("완료"),
    INCOMPLETE("미완료");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
