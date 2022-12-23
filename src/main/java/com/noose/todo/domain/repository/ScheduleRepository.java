package com.noose.todo.domain.repository;

import com.noose.todo.domain.schedule.ScheduleNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleNote, Long> {
}
