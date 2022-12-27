package com.noose.todo.domain.note;

import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.domain.note.entity.Todo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Todos {
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "note",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Todo> values = new ArrayList<>();

    public Todos() {
    }

    public Todos(List<Todo> values) {
        this.values.addAll(values);
    }

    public boolean isComplete() {
        return values.stream()
                .allMatch(Todo::isComplete);
    }

    public int size() {
        return values.size();
    }

    public void add(Todo todo) {
        values.add(todo);
    }

    public void addAll(Todos todos) {
        if (todos == null) {
            return;
        }
        this.values.addAll(todos.values);
    }

    public void setNote(Note note) {
        values.forEach(todo -> todo.setNote(note));
    }

    public void delete(Todo todo) {
        values.remove(todo);
    }
}
