package com.noose.todo.controller;

import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.dto.request.NoteRequest;
import com.noose.todo.dto.request.UpdateNoteRequest;
import com.noose.todo.dto.response.NoteResponse;
import com.noose.todo.dto.response.Response;
import com.noose.todo.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
@RestController
@Deprecated
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public Response<NoteResponse> createNote(@RequestBody @Valid NoteRequest request) {
        Note newNote = request.toEntity();

        noteService.save(newNote);

        return Response.ok(NoteResponse.from(newNote));
    }

    @GetMapping
    public Response<Page<NoteResponse>> getNotes(Pageable pageable) {
        return Response.ok(noteService.searchAll(pageable));
    }

    @GetMapping("/{noteId}")
    public Response<NoteResponse> getNote(@PathVariable Long noteId) {
        return Response.ok(noteService.searchById(noteId));
    }

    @PutMapping("/{noteId}")
    public Response<NoteResponse> updateNote(@PathVariable Long noteId, @RequestBody UpdateNoteRequest request) {
        return Response.ok(noteService.update(noteId, request));
    }

    @DeleteMapping("/{noteId}")
    public Response<String> deleteNote(@PathVariable Long noteId) {
        noteService.delete(noteId);
        return Response.ok("OK");
    }
}
