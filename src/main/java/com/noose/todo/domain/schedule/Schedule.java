package com.noose.todo.domain.schedule;

public class Schedule {

    private Title title;
    private String body;

    public Schedule(String title, String body) {
        this.title = new Title(title);
        this.body = body;
    }
}
