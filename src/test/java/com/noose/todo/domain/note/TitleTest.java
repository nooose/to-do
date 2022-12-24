package com.noose.todo.domain.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Title 도메인 테스트")
class TitleTest {

    @DisplayName("null 또는 빈 문자열인 경우 예외가 발생한다.")
    @ParameterizedTest(name = "입력값: {0}")
    @NullAndEmptySource
    void nullAndEmptyExceptionTest(String title) {
        assertThatThrownBy(() -> new Title(title))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("길이가 1 미만 20 초과인 경우 예외가 발생한다.")
    @ParameterizedTest(name = "입력값: {0}")
    @MethodSource
    void lengthExceptionTest(String title) {
        assertThatThrownBy(() -> new Title(title))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> lengthExceptionTest() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("a".repeat(21))
        );
    }
}
