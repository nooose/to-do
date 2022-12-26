package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.NoteHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteHashtagRepository extends JpaRepository<NoteHashtag, Long> {

}
