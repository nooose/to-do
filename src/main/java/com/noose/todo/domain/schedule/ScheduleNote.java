package com.noose.todo.domain.schedule;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ScheduleNote extends Note {
    @Embedded
    private Period period;

    @OneToMany(mappedBy = "scheduleNote")
    private List<NoteHashtag> noteHashtags = new ArrayList<>();

    @Embedded
    private Todos todos;

    public static ScheduleNote of(String title) {
        return of(title, "");
    }

    public static ScheduleNote of(String title, String body) {
        Body newBody = new Body(body);
        return new ScheduleNote(null, new Title(title), new Body(body), new Period(), new Todos());
    }

    private ScheduleNote(Long id, Title title, Body body, Period period, Todos todos) {
        super(id, title, body);
        this.period = period;
        this.todos = todos;
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setScheduleNote(this);
    }

    public void addTodos(Todos todos) {
        this.todos.addAll(todos);
        todos.setNote(this);
    }

    public int todoSize() {
        return todos.size();
    }

    public void deleteTodo(Todo todo) {
        todos.delete(todo);
    }
}
