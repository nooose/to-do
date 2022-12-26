package com.noose.todo.domain.note.entity;

import com.noose.todo.domain.note.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DiscriminatorValue("S")
@Entity
public class ScheduleNote extends Note {
    @Embedded
    private Period period;

    @OneToMany(mappedBy = "note")
    private List<NoteHashtag> noteHashtags = new ArrayList<>();

    @Embedded
    private Todos todos = new Todos();

    private ScheduleNote(Long id, Title title, Body body, Period period, Todos todos) {
        super(id, title, body);
        this.period = period;
        addTodos(todos);
    }

    public static ScheduleNote of(String title) {
        return of(title, "");
    }

    public static ScheduleNote of(String title, String body) {
        return of(title, body, new Todos());
    }

    public static ScheduleNote of(String title, String body, Todos todos) {
        return new ScheduleNote(null, new Title(title), new Body(body), new Period(), todos);
    }

    @Override
    public List<Todo> todos() {
        return todos.getValues();
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setNote(this);
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