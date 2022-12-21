package com.noose.todo.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Schedule 도메인 테스트")
class ScheduleNoteTest {

    @DisplayName("제목, 내용으로 생성할 수 있다.")
    @Test
    void createScheduleTest() {
        assertDoesNotThrow(() -> new ScheduleNote("title", "body"));
    }

    @DisplayName("제목만 입력한 경우 본문 내용은 비어있다.")
    @Test
    void titleOnlyTest() {
        ScheduleNote scheduleNote = new ScheduleNote("title");

        assertThat(scheduleNote.isEmptyBody()).isTrue();
    }

    @DisplayName("제목이 비어 있는 경우 예외가 발생한다.")
    @NullAndEmptySource
    @ParameterizedTest
    void createTitleExceptionScheduleTest(String title) {
        assertThatThrownBy(() -> new ScheduleNote(title))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Todo 를 추가할 수 있다.")
    @Test
    void addTodoTest() {
        ScheduleNote scheduleNote = new ScheduleNote("title", "body");

        scheduleNote.addTodo(TodoTest.getTodoFixture("할 일"));

        assertThat(scheduleNote.todoSize()).isEqualTo(1);
    }
}
