package com.noose.todo.controller;

import com.noose.todo.controller.dto.request.NoteRequest;
import com.noose.todo.controller.dto.request.UpdateNoteRequest;
import com.noose.todo.controller.dto.response.NoteResponse;
import com.noose.todo.controller.dto.response.Response;
import com.noose.todo.domain.note.Note;
import com.noose.todo.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
@RestController
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public Response<NoteResponse> createNote(@RequestBody @Valid NoteRequest request) {
        Note newNote = request.toEntity();

        noteService.save(newNote);

        return Response.ok(NoteResponse.from(newNote));
    }

    @GetMapping
    public Response<List<NoteResponse>> getNotes() {
        return Response.ok(noteService.searchAll()
                .stream()
                .map(NoteResponse::from)
                .toList());
    }

    @GetMapping("/{noteId}")
    public Response<NoteResponse> getNote(@PathVariable Long noteId) {
        return Response.ok(NoteResponse.from(noteService.searchById(noteId)));
    }

    @PutMapping("/{noteId}")
    public Response<NoteResponse> updateNote(@PathVariable Long noteId, @RequestBody UpdateNoteRequest request) {
        return Response.ok(NoteResponse.from(noteService.update(noteId, request)));
    }

    @DeleteMapping("/{noteId}")
    public Response<String> deleteNote(@PathVariable Long noteId) {
        noteService.delete(noteId);
        return Response.ok("OK");
    }
}
