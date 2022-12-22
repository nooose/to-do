package com.noose.todo.domain.Repository;

import com.noose.todo.domain.schedule.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
