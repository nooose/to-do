package com.noose.todo.service;

import com.noose.todo.controller.dto.request.UpdateNoteRequest;
import com.noose.todo.domain.repository.NoteRepository;
import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.exception.TodoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional
    public void save(Note note) {
        noteRepository.save(note);
    }

    public Note searchById(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> {
            throw new TodoException(HttpStatus.NOT_FOUND, noteId + "번 데이터를 찾을 수 없습니다.");
        });
    }

    public List<Note> searchAll() {
        return noteRepository.findAll();
    }

    @Transactional
    public Note update(Long noteId, UpdateNoteRequest request) {
        Note updateNote = searchById(noteId);
        updateNote.update(request.title(), request.body());

        return updateNote;
    }

    @Transactional
    public void delete(Long noteId) {
        Note deleteNote = searchById(noteId);
        noteRepository.delete(deleteNote);
    }
}
