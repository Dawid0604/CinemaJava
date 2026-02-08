/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Reservation {
    private final Long id;
    private final long screeningId;
    private final ReservationStatus status;
    private final Email customerEmail;
    private final Money totalPrice;
    private final LocalDateTime expiresAt;

    @Getter(NONE)
    private final Set<HallSeat> seats;

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static Result<Reservation> create(
            final Long id,
            final Long screeningId,
            final ReservationStatus status,
            final Email customerEmail,
            final Money totalPrice,
            final LocalDateTime expiresAt,
            final Set<HallSeat> seats) {

        if (id != null && id < 0) {
            return createFailure("Id cannot be negative");
        }

        if (screeningId == null || screeningId < 0) {
            return createFailure("ScreeningId cannot be null or negative");
        }

        if (customerEmail == null) {
            return createFailure("Email cannot be null");
        }

        if (totalPrice == null) {
            return createFailure("TotalPrice cannot be null");
        }

        if (expiresAt == null) {
            return createFailure("ExpiresAt cannot be null");
        }

        if (status == null) {
            return createFailure("Status cannot be null");
        }

        if (seats == null || seats.isEmpty()) {
            return createFailure("Seats cannot be null or empty");
        }

        return new Result.Success<>(
                new Reservation(
                        id, screeningId, status, customerEmail, totalPrice, expiresAt, seats));
    }

    public Set<HallSeat> getSeats() {
        return Set.copyOf(seats);
    }

    private static Result<Reservation> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }
}
