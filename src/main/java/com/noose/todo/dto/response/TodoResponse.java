package com.noose.todo.dto.response;

import com.noose.todo.domain.note.entity.Todo;

public record TodoResponse(
        Long id,
        String contents
) {
    public static TodoResponse from(Todo entity) {
        return new TodoResponse(entity.getId(), entity.getContents());
    }
}
