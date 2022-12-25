package com.noose.todo.domain.note;

import com.noose.todo.domain.note.entity.ScheduleNote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("ScheduleNote 도메인 테스트")
class ScheduleNoteTest {

    @DisplayName("제목이 비어 있는 경우 예외가 발생한다.")
    @NullAndEmptySource
    @ParameterizedTest(name = "입력값: {0}")
    void createTitleExceptionScheduleTest(String title) {
        assertThatThrownBy(() -> ScheduleNote.of(title))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("제목만 입력한 경우 본문 내용은 비어있다.")
    @Test
    void titleOnlyTest() {
        ScheduleNote scheduleNote = ScheduleNote.of("title");

        assertThat(scheduleNote.isEmptyBody()).isTrue();
    }

    @DisplayName("Todo 를 추가할 수 있다.")
    @Test
    void addTodoTest() {
        ScheduleNote scheduleNote = ScheduleNote.of("title", "body");

        scheduleNote.addTodo(TodoTest.getTodoFixture("할 일"));

        assertThat(scheduleNote.todoSize()).isEqualTo(1);
    }


    @DisplayName("제목과 본문으로 생성하는 경우 해시태그, 기한이 설정된다.")
    @Test
    void createScheduleNote() {
        String title = "테스트";
        String body = "테스트 글 #테스트 #first #test";
        LocalDateTime baseTime = LocalDateTime.now();
        ScheduleNote scheduleNote = ScheduleNote.of(title, body);

        assertAll(() -> {
            assertThat(scheduleNote.getTitle()).isEqualTo(new Title(title));
            assertThat(scheduleNote.getBody()).isEqualTo(new Body(body));
            assertThat(scheduleNote.todoSize()).isEqualTo(0);
            assertThat(scheduleNote.getPeriod().getStartDate()).isAfter(baseTime);
        });

        System.out.println(scheduleNote);
    }
}
