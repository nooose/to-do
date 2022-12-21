package com.noose.todo.domain.schedule;

public class Schedule {

    private Title title;
    private Body body;
    private Todos todos;

    public Schedule(String title) {
        this(title, "");
    }

    public Schedule(String title, String body) {
        this.title = new Title(title);
        this.body = new Body(body);
        this.todos = new Todos();
    }

    public boolean isEmptyBody() {
        return body.isEmpty();
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public int todoSize() {
        return todos.size();
    }
}
