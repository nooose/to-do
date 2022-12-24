package com.noose.todo.controller.dto.response;

import com.noose.todo.domain.schedule.Note;

public record NoteResponse(
        Long id,
        String title,
        String body
) {
    public static NoteResponse from(Note entity) {
        return new NoteResponse(
                entity.getId(),
                entity.getTitle().getValue(),
                entity.getBody().getContents());
    }
}
