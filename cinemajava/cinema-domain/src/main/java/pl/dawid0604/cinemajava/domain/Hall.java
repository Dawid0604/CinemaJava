/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Hall {
    private final Long id;
    private final String name;

    public static Result<Hall> create(final Long id, final String name) {
        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (isBlank(name)) {
            return createFailure("Name cannot be blank");
        }

        return new Result.Success<>(new Hall(id, name));
    }

    private static Result<Hall> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }
}
