package com.noose.todo.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Schedule 도메인 테스트")
class ScheduleTest {

    @DisplayName("제목, 내용으로 생성할 수 있다.")
    @Test
    void createScheduleTest() {
        assertDoesNotThrow(() -> new Schedule("title", "body"));
    }
}
