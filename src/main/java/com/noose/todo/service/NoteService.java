package com.noose.todo.service;

import com.noose.todo.dto.request.UpdateNoteRequest;
import com.noose.todo.domain.repository.NoteRepository;
import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.dto.response.NoteResponse;
import com.noose.todo.exception.TodoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional
    public void save(Note note) {
        noteRepository.save(note);
    }

    public NoteResponse searchById(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> {
            throw new TodoException(HttpStatus.NOT_FOUND, noteId + "번 데이터를 찾을 수 없습니다.");
        });

        return NoteResponse.from(note);
    }

    public List<NoteResponse> searchAll() {
        return noteRepository.findAll()
                .stream()
                .map(NoteResponse::from)
                .toList();
    }

    @Transactional
    public NoteResponse update(Long noteId, UpdateNoteRequest request) {
        Note updateNote = noteRepository.findById(noteId).orElseThrow(() -> {
            throw new TodoException(HttpStatus.NOT_FOUND, noteId + "번 데이터를 찾을 수 없습니다.");
        });

        updateNote.update(request.title(), request.body());

        return NoteResponse.from(updateNote);
    }

    @Transactional
    public void delete(Long noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new TodoException(HttpStatus.NOT_FOUND, noteId + "번 데이터를 찾을 수 없습니다.");
        }

        noteRepository.deleteById(noteId);
    }
}
