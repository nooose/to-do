package com.noose.todo.domain.schedule;

import java.util.ArrayList;
import java.util.List;

public class Todos {
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
