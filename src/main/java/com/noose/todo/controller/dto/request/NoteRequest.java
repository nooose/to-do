package com.noose.todo.controller.dto.request;

import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.domain.note.entity.ScheduleNote;
import com.noose.todo.domain.note.entity.Todo;
import com.noose.todo.domain.note.Todos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NoteRequest(

    @NotBlank(message="제목은 공백일 수 없습니다.")
    String title,
    @NotNull
    String body,
    List<String> todos
) {

    public Note toEntity() {
        if (todos == null || todos.isEmpty()) {
            return Note.of(title, body);
        }

        Todos newTodos = new Todos(todos.stream()
                .map(Todo::of)
                .toList());
        return ScheduleNote.of(title, body, newTodos);
    }
}
