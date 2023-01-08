package com.noose.todo.dto.response;

import com.noose.todo.domain.note.Status;
import com.noose.todo.domain.note.entity.Todo;

public record TodoResponse(
        Long id,
        String contents,
        Status status
) {
    public static TodoResponse from(Todo entity) {
        return new TodoResponse(entity.getId(), entity.getContents(), entity.getStatus());
    }
}
