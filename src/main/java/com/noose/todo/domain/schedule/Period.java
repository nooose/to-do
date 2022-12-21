package com.noose.todo.domain.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
        LocalDateTime now = LocalDateTime.now();
        this.startDate = now;
        this.endDate = now;
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 빠를 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
