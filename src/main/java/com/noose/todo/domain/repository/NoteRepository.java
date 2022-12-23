package com.noose.todo.domain.repository;

import com.noose.todo.domain.schedule.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
