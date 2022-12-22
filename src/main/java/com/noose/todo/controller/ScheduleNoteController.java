package com.noose.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/notes")
@RestController
public class ScheduleNoteController {

    @PostMapping
    public ResponseEntity<String> createNote() {
        // TODO: Note 저장 로직
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<List<String>> getNotes() {
        // TODO: Note 컬렉션 조회 로직
        return ResponseEntity.ok(List.of("A", "B"));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<String> getNote(@PathVariable String noteId) {
        // TODO: Note 조회 로직
        return ResponseEntity.ok(noteId);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<String> updateNote(@PathVariable String noteId) {
        // TODO: Note 업데이트 로직
        return ResponseEntity.ok(noteId);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable String noteId) {
        // TODO: Note 삭제 로직
        return ResponseEntity.ok(noteId);
    }
}
