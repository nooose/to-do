package com.noose.todo.dto.request;

import com.noose.todo.domain.note.Status;
import com.noose.todo.domain.note.entity.Todo;

public record TodoRequest(
        String todo,
        boolean status
) {

    public Todo toEntity() {
        if (!status) {
            return Todo.of(todo, Status.INCOMPLETE);
        }

        return Todo.of(todo, Status.COMPLETE);
    }
}
