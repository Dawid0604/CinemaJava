/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Money {
    private final BigDecimal amount;

    public static Result<Money> create(final BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            return new Result.Failure<>(
                    Result.Problem.validation("Amount cannot be null or negative"));
        }

        return new Result.Success<>(new Money(value));
    }
}
