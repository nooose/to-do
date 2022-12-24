package com.noose.todo.controller.dto.response;

import com.noose.todo.domain.schedule.Todo;

public record TodoResponse(
        Long id,
        String contents
) {
    public static TodoResponse from(Todo entity) {
        return new TodoResponse(entity.getId(), entity.getContents());
    }
}
