package com.noose.todo.domain.schedule;

public class ScheduleNote {

    private Title title;
    private Body body;
    private Todos todos;
    private Period period;

    public ScheduleNote(String title) {
        this(title, "");
    }

    public ScheduleNote(String title, String body) {
        this(new Title(title), new Body(body), new Todos(), new Period());
    }

    public ScheduleNote(Title title, Body body, Todos todos, Period period) {
        this.title = title;
        this.body = body;
        this.todos = todos;
        this.period = period;
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
