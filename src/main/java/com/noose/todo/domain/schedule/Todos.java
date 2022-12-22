package com.noose.todo.domain.schedule;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Todos {
    @OneToMany(mappedBy = "scheduleNote")
    private List<Todo> todos = new ArrayList<>();

    public Todos() {
    }

    public Todos(List<Todo> todos) {
        this.todos.addAll(todos);
    }

    public boolean isComplete() {
        return todos.stream()
                .allMatch(Todo::isComplete);
    }

    public int size() {
        return todos.size();
    }

    public void add(Todo todo) {
        todos.add(todo);
    }
}
