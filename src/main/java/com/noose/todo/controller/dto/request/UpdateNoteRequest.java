package com.noose.todo.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateNoteRequest(
        @NotBlank(message="제목은 공백일 수 없습니다.")
        String title,
        @NotNull
        String body
) {
}
