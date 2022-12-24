package com.noose.todo.domain.repository;

import com.noose.todo.config.JpaConfiguration;
import com.noose.todo.domain.schedule.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("[Repository] ScheduleRepository 테스트")
@Import(JpaConfiguration.class)
@DataJpaTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private NoteRepository noteRepository;

    @DisplayName("Entity를 저장할 수 있다.")
    @Test
    void saveScheduleNoteTest() {
        ScheduleNote scheduleNote = ScheduleNote.of("목요일 저녁은?", "햄버거!");

        ScheduleNote result = scheduleRepository.save(scheduleNote);

        assertThat(result.getId()).isNotNull();
    }

    @DisplayName("Todo Entity와 양방향 매핑이 잘 되어있다.")
    @Test
    void saveTodoTest() {
        Todo todo = Todo.of("테스트");
        ScheduleNote scheduleNote = ScheduleNote.of("목요일 저녁은?", "햄버거!");

        scheduleNote.addTodo(todo);
        Todo newTodo = todoRepository.save(todo);
        scheduleRepository.save(scheduleNote);

        ScheduleNote findScheduleNote = scheduleRepository.findById(scheduleNote.getId()).get();
        Todo findTodo = todoRepository.findById(newTodo.getId()).get();
        assertAll(() -> {
            assertThat(findScheduleNote.getTodos().size()).isEqualTo(1);
            assertThat(findTodo.getNote()).isEqualTo(findScheduleNote);
        });
    }

    @DisplayName("제목과 본문내용을 수정할 수 있다.")
    @Test
    void updateScheduleTest() {
        String updateTitle = "내일 저녁은";
        String updateBody = "피자";
        ScheduleNote scheduleNote = ScheduleNote.of("목요일 저녁은?", "햄버거!");
        ScheduleNote result = scheduleRepository.save(scheduleNote);

        result.update(updateTitle, updateBody);
        ScheduleNote findScheduleNote = scheduleRepository.findById(scheduleNote.getId()).get();

        assertAll(() -> {
            assertThat(findScheduleNote.getTitle()).isEqualTo(new Title(updateTitle));
            assertThat(findScheduleNote.getBody()).isEqualTo(new Body(updateBody));
        });
    }

    @DisplayName("Entity 를 삭제할 때 연관관계가 맺어진 Todo 도 삭제된다.")
    @Test
    void deleteScheduleTest() {
        Todo todo = Todo.of("테스트");
        ScheduleNote scheduleNote = ScheduleNote.of("목요일 저녁은?", "햄버거!");

        scheduleNote.addTodo(todo);
        todoRepository.save(todo);
        scheduleRepository.save(scheduleNote);

        scheduleRepository.delete(scheduleNote);

        Optional<Todo> resultTodo = todoRepository.findById(todo.getId());
        Optional<ScheduleNote> resultScheduleNote = scheduleRepository.findById(scheduleNote.getId());

        assertAll(() -> {
            assertThat(resultTodo).isEmpty();
            assertThat(resultScheduleNote).isEmpty();
        });
    }

    @DisplayName("조회한 Entity 에서 Todo 객체를 지웠을 때 대상 Todo 는 삭제된다.")
    @Test
    void deleteTodosTest() {
        Todo todo1 = Todo.of("빅맥 먹기");
        Todo todo2 = Todo.of("베토디 먹기");
        Todo todo3 = Todo.of("불고기버거 먹기");
        ScheduleNote scheduleNote = ScheduleNote.of("목요일 저녁은?", "햄버거!");
        Todos todos = new Todos(List.of(todo1, todo2, todo3));
        scheduleNote.addTodos(todos);
        scheduleRepository.save(scheduleNote);

        ScheduleNote result = scheduleRepository.findById(scheduleNote.getId()).get();
        result.deleteTodo(todo3);
        scheduleRepository.flush();

        assertAll(() -> {
            assertThat(result.getTodos().size()).isEqualTo(2);
            assertThat(todoRepository.findById(todo3.getId())).isEmpty();
        });
    }

    @DisplayName("데이터 저장 시 현재시간도 같이 저장된다.")
    @Test
    void findScheduleNote() {
        ScheduleNote scheduleNote = ScheduleNote.of("오늘은 금요일", "이따 저녁에 뭐먹지?");
        scheduleRepository.save(scheduleNote);

        ScheduleNote findScheduleNote = scheduleRepository.findById(scheduleNote.getId()).get();

        LocalDateTime now = LocalDateTime.now();
        assertAll(() -> {
            assertThat(findScheduleNote.getCreatedAt()).isBefore(now);
            assertThat(findScheduleNote.getModifiedAt()).isBefore(now);
        });
    }
}
