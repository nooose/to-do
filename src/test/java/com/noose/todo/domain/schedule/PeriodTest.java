package com.noose.todo.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("시작일과 종료일에 관한 테스트")
class PeriodTest {

    @DisplayName("종료일과 시작일의 기본값은 현재 시간으로 설정된다.")
    @Test
    void defaultDateTest() {
        Period period = new Period();

        assertAll(() -> {
            assertThat(period.getStartDate()).isEqualToIgnoringMinutes(LocalDateTime.now());
            assertThat(period.getEndDate()).isEqualToIgnoringMinutes(LocalDateTime.now());
        });
    }

    @DisplayName("종료일이 시작일보다 빠른경우 예외가 발생한다")
    @Test
    void selectEndDateTest() {
        LocalDateTime startDate = LocalDateTime.of(2022, 12, 30, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 12, 12, 0, 0);

        assertThatThrownBy(() -> new Period(startDate, endDate)).isInstanceOf(IllegalArgumentException.class);
    }
}
