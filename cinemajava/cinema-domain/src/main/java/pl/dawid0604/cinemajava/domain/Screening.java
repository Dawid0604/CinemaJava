/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static java.time.temporal.ChronoUnit.SECONDS;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Screening {
    private final Long id;
    private final long movieId;
    private final long hallId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Money price;

    @Getter(NONE)
    private final List<ScreeningType> types;

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static Result<Screening> create(
            final Long id,
            final Long movieId,
            final Long hallId,
            final LocalDateTime startTime,
            final LocalDateTime endTime,
            final Money price,
            final List<ScreeningType> types) {

        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (movieId == null || movieId < 0) {
            return createFailure("Movie cannot be null or negative");
        }

        if (hallId == null || hallId < 0) {
            return createFailure("Hall cannot be null or negative");
        }

        if (startTime == null) {
            return createFailure("Start time cannot be null");
        }

        if (endTime == null) {
            return createFailure("End time cannot be null");
        }

        if (startTime.truncatedTo(SECONDS).isEqual(endTime.truncatedTo(SECONDS))) {
            return createFailure("Start time cannot be equal to end time");
        }

        if (startTime.isAfter(endTime)) {
            return createFailure("Start time cannot be after end time");
        }

        if (price == null) {
            return createFailure("Price cannot be null");
        }

        if (types == null || types.isEmpty()) {
            return createFailure("Types cannot be null or empty");
        }

        return new Result.Success<>(
                new Screening(id, movieId, hallId, startTime, endTime, price, types));
    }

    private static Result<Screening> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }

    public List<ScreeningType> getTypes() {
        return List.copyOf(types);
    }
}
