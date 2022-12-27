package com.noose.todo.service;

import com.noose.todo.domain.note.entity.Hashtag;
import com.noose.todo.domain.note.entity.Note;
import com.noose.todo.domain.repository.NoteRepository;
import com.noose.todo.dto.request.UpdateNoteRequest;
import com.noose.todo.dto.response.NoteResponse;
import com.noose.todo.exception.TodoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final HashtagService hashtagService;
    private final NoteRepository noteRepository;

    @Transactional
    public void save(Note note) {
        List<String> hashtagNames = note.getBody().parseHashtags();
        Set<Hashtag> existingHashtags = hashtagService.findAllByHashtagNames(hashtagNames);
        note.syncHashtags(existingHashtags);
        noteRepository.save(note);
    }

    public NoteResponse searchById(Long noteId) {
        return NoteResponse.from(search(noteId));
    }

    public Page<NoteResponse> searchAll(Pageable pageable) {
        return noteRepository.findAll(pageable)
                .map(NoteResponse::from);
    }

    @Transactional
    public NoteResponse update(Long noteId, UpdateNoteRequest request) {
        Note note = search(noteId);
        note.update(request.title(), request.body());

        List<Hashtag> hashtags = note.hashTags();

        List<String> hashtagNames = note.hashtagNamesInBody();
        Set<Hashtag> existingHashtags = hashtagService.findAllByHashtagNames(hashtagNames);
        note.clearHashtags();
        note.syncHashtags(existingHashtags);

        noteRepository.flush();
        hashtagService.deleteUnusedHashtags(hashtags);
        return NoteResponse.from(note);
    }

    @Transactional
    public void delete(Long noteId) {
        Note note = search(noteId);
        List<Hashtag> hashtags = note.hashTags();
        noteRepository.deleteById(noteId);
        noteRepository.flush();
        hashtagService.deleteUnusedHashtags(hashtags);
    }

    private Note search(Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> {
            throw new TodoException(HttpStatus.NOT_FOUND, noteId + "번 데이터를 찾을 수 없습니다.");
        });
    }
}
