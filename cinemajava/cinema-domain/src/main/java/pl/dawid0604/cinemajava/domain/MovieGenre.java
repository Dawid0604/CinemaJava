/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class MovieGenre {
    private final Long id;
    private final long tmdbId;
    private final String name;

    public static Result<MovieGenre> create(final Long id, final Long tmdbId, final String name) {
        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (tmdbId == null || tmdbId < 0) {
            return createFailure("TmdbId cannot be null or negative");
        }

        if (isBlank(name)) {
            return createFailure("Name cannot be blank");
        }

        return new Result.Success<>(new MovieGenre(id, tmdbId, name));
    }

    private static Result<MovieGenre> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }
}
