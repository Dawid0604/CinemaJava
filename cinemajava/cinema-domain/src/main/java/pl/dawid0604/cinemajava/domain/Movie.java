/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Movie {
    private final Long id;
    private final long tmdbId;
    private final String title;
    private final String backdropPath;
    private final String posterPath;
    private final LocalDate releaseDate;

    @Getter(NONE)
    private final List<MovieGenre> genres;

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static Result<Movie> create(
            final Long id,
            final Long tmdbId,
            final String title,
            final String backdropPath,
            final String posterPath,
            final LocalDate releaseDate,
            final List<MovieGenre> genres) {

        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (tmdbId == null || tmdbId < 0) {
            return createFailure("TmdbId cannot be null or negative");
        }

        if (isBlank(title)) {
            return createFailure("Title cannot be blank");
        }

        if (releaseDate == null) {
            return createFailure("Release date cannot be null");
        }

        if (genres == null || genres.isEmpty()) {
            return createFailure("Genres cannot be null or empty");
        }

        return new Result.Success<>(
                new Movie(id, tmdbId, title, backdropPath, posterPath, releaseDate, genres));
    }

    private static Result<Movie> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }

    public List<MovieGenre> getGenres() {
        return List.copyOf(genres);
    }
}
