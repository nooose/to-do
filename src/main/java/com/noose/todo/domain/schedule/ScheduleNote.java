package com.noose.todo.domain.schedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ScheduleNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_note_id")
    private Long id;
    @Embedded
    private Title title;
    @Embedded
    private Body body;
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
        this.id = id;
        this.title = title;
        this.body = body;
        this.period = period;
        this.todos = todos;
    }

    public boolean isEmptyBody() {
        return body.isEmpty();
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

    public void updateTitle(String title) {
        this.title = new Title(title);
    }

    public void updateBody(String body) {
        this.body = new Body(body);
    }

    public void deleteTodo(Todo todo) {
        todos.delete(todo);
    }
}
