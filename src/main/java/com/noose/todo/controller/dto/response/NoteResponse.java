package com.noose.todo.controller.dto.response;

import com.noose.todo.domain.note.entity.Note;

import java.util.List;

public record NoteResponse(
        Long id,
        String title,
        String body,
        List<TodoResponse> todos
) {
    public static NoteResponse from(Note entity) {
        return new NoteResponse(
                entity.getId(),
                entity.getTitle().getValue(),
                entity.getBody().getContents(),
                entity.todos()
                        .stream()
                        .map(TodoResponse::from)
                        .toList()
        );
    }
}
