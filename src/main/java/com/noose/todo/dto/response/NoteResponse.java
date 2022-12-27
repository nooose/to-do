package com.noose.todo.dto.response;

import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.domain.note.entity.NoteHashtag;

import java.util.List;

public record NoteResponse(
        Long id,
        String title,
        String body,
        List<TodoResponse> todos,
        List<String> hashtags
) {
    public static NoteResponse from(Note entity) {
        return new NoteResponse(
                entity.getId(),
                entity.getTitle().getValue(),
                entity.getBody().getContents(),
                entity.todos()
                        .stream()
                        .map(TodoResponse::from)
                        .toList(),
                entity.getNoteHashtags()
                        .stream()
                        .map(NoteHashtag::hashtagName)
                        .toList()
        );
    }
}
