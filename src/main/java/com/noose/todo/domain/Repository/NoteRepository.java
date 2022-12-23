package com.noose.todo.domain.Repository;

import com.noose.todo.domain.schedule.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
