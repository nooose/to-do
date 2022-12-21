package com.noose.todo.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleNote {

    private Title title;
    private Body body;

    private Hashtags hashtags;
    private Todos todos;
    private Period period;

    public static ScheduleNote of(String title) {
        return of(title, "");
    }

    public static ScheduleNote of(String title, String body) {
        Body newBody = new Body(body);
        return new ScheduleNote(new Title(title), newBody, new Hashtags(newBody), new Todos(), new Period());
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
