package com.noose.todo.domain.repository;

import com.noose.todo.domain.note.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
