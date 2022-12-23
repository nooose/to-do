package com.noose.todo.domain.schedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Todo extends AuditingFields {

    private static final int MAX_LENGTH = 30;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    private String contents;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_note_id")
    private ScheduleNote scheduleNote;

    public Todo(String contents) {
        if (contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("글자수는 빈값이거나 null 일 수 없습니다.");
        }

        if (contents.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH + " 글자를 초과할 수 없습니다.");
        }

        this.contents = contents;
        this.status = Status.INCOMPLETE;
    }

    public static Todo of(String contents) {
        return new Todo(contents);
    }

    public void setScheduleNote(ScheduleNote scheduleNote) {
        this.scheduleNote = scheduleNote;
    }

    public void complete() {
        this.status = Status.COMPLETE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }
}
