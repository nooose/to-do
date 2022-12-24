package com.noose.todo.domain.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Todo 일급컬렉션 도메인 테스트")
class TodosTest {

    @DisplayName("모든 Todo 의 상태가 완료되었는지 확인 할 수 있다.")
    @Test
    void completeTest() {
        Todo todoA = TodoTest.getTodoFixture("할 일");
        Todo todoB = TodoTest.getTodoFixture("할 일");
        Todos todos = new Todos(List.of(todoA, todoB));

        todoA.complete();
        todoB.complete();

        assertThat(todos.isComplete()).isTrue();
    }

    @DisplayName("모든 Todo 중 하나라도 미완료인 경우 미완료 상태이다.")
    @Test
    void incompleteTest() {
        Todo todoA = TodoTest.getTodoFixture("할 일");
        Todo todoB = TodoTest.getTodoFixture("할 일");
        Todos todos = new Todos(List.of(todoA, todoB));

        todoA.complete();

        assertThat(todos.isComplete()).isFalse();
    }

    @DisplayName("Todo 를 추가할 수 있다.")
    @Test
    void addTest() {
        Todo todoA = TodoTest.getTodoFixture("할 일");
        Todo todoB = TodoTest.getTodoFixture("할 일");
        Todos todos = new Todos(List.of(todoA, todoB));

        todos.add(TodoTest.getTodoFixture("할 일"));

        assertThat(todos.size()).isEqualTo(3);
    }
}
