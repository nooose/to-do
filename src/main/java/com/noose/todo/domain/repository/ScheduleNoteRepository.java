package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.ScheduleNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleNoteRepository extends JpaRepository<ScheduleNote, Long> {
}
