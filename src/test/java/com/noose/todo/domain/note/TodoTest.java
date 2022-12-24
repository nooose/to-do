package com.noose.todo.domain.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Todo 도메인 테스트")
class TodoTest {

    @DisplayName("내용이 null 또는 빈 문자열인 경우 예외가 발생한다.")
    @ParameterizedTest(name = "입력값: {0}")
    @NullAndEmptySource
    void nullAndEmptyExceptionTest(String contents) {
        assertThatThrownBy(() -> getTodoFixture(contents))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("내용이 30 글자를 초과하는 경우 예외가 발생한다.")
    @Test
    void lengthExceptionTest() {
        assertThatThrownBy(() -> getTodoFixture("a".repeat(31)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기본은 미완료 상태이다.")
    @Test
    void createTest() {
        Todo todo = getTodoFixture("할 일");

        assertThat(todo.isComplete()).isFalse();
    }

    @DisplayName("완료했는지 설정 할 수 있다.")
    @Test
    void completeTest() {
        Todo todo = getTodoFixture("할 일");

        todo.complete();

        assertThat(todo.isComplete()).isTrue();
    }

    public static Todo getTodoFixture(String contents) {
        return new Todo(contents);
    }
}
