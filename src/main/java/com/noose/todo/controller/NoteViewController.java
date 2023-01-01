package com.noose.todo.controller;

import com.noose.todo.dto.request.NoteRequest;
import com.noose.todo.dto.request.UpdateNoteRequest;
import com.noose.todo.dto.response.NoteResponse;
import com.noose.todo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/notes")
@Controller
public class NoteViewController {

    private final NoteService noteService;

    @GetMapping("/new")
    public String noteForm(Model model) {
        model.addAttribute("status", FormStatus.CREATE);
        return "notes/noteForm";
    }

    @PostMapping("/new")
    public String createNote(NoteRequest noteRequest) {
        noteService.save(noteRequest.toEntity());

        return "redirect:/notes";
    }

    @GetMapping
    public String getNotes(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<NoteResponse> notes = noteService.searchAll(pageable);

        model.addAttribute("notes", notes);
        return "notes/notes";
    }

    @GetMapping("/{noteId}")
    public String getNote(@PathVariable Long noteId, Model model) {
        NoteResponse note = noteService.searchById(noteId);

        model.addAttribute("status", FormStatus.UPDATE);
        model.addAttribute("note", note);
        return "notes/noteForm";
    }

    @PostMapping("/{noteId}/update")
    public String updateForm(@PathVariable Long noteId, UpdateNoteRequest request) {
        noteService.update(noteId, request);

        return "redirect:/notes";
    }
}
