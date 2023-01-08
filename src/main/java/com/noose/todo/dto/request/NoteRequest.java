package com.noose.todo.dto.request;

import com.noose.todo.domain.note.Todos;
import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.domain.note.entity.ScheduleNote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NoteRequest(

    @NotBlank(message="제목은 공백일 수 없습니다.")
    String title,
    @NotNull
    String body,
    List<TodoRequest> todos
) {

    public Note toEntity() {
        if (todos == null || todos.isEmpty()) {
            return Note.of(title, body);
        }

        Todos newTodos = new Todos(todos.stream()
                .map(TodoRequest::toEntity)
                .toList());
        return ScheduleNote.of(title, body, newTodos);
    }

    public static NoteRequest empty() {
        return new NoteRequest("", "", List.of());
    }
}
