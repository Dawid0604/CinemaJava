/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class HallSeat {
    private final Long id;
    private final long hallId;
    private final int rowNum;
    private final int seatNum;
    private final HallSeatType type;

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static Result<HallSeat> create(
            final Long id,
            final Long hallId,
            final Integer rowNum,
            final Integer seatNum,
            final HallSeatType type) {

        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (hallId == null || hallId < 0) {
            return createFailure("HallId cannot be negative");
        }

        if (rowNum == null || rowNum < 0) {
            return createFailure("RowNum cannot be null or negative");
        }

        if (seatNum == null || seatNum < 0) {
            return createFailure("SeatNum cannot be null or negative");
        }

        if (type == null) {
            return createFailure("Type cannot be null");
        }

        return new Result.Success<>(new HallSeat(id, hallId, rowNum, seatNum, type));
    }

    private static Result<HallSeat> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }
}
