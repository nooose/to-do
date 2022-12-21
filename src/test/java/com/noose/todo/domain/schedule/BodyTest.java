package com.noose.todo.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Body 도메인 테스트")
class BodyTest {

    @DisplayName("null 인 경우 예외가 발생한다")
    @NullSource
    @ParameterizedTest(name = "입력값: {0}")
    void bodyNullAndEmptyTest(String body) {
        assertThatThrownBy(() -> new Body(body)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("길이가 500보다 큰 경우 예외가 발생한다.")
    @ParameterizedTest(name = "입력값: {0}")
    @MethodSource
    void lengthExceptionTest(String body) {
        assertThatThrownBy(() -> new Body(body))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> lengthExceptionTest() {
        return Stream.of(
                Arguments.of("a".repeat(501))
        );
    }
}
