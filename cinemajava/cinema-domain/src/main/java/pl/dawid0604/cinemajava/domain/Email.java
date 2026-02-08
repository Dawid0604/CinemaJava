/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.dawid0604.cinemajava.domain.common.Result;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public final class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private final String value;

    public static Result<Email> create(final String value) {
        if (isBlank(value)) {
            return createFailure("Email cannot be null or blank");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            return createFailure("Email is invalid");
        }

        return new Result.Success<>(new Email(value));
    }

    private static Result<Email> createFailure(final String message) {
        return new Result.Failure<>(Result.Problem.validation(message));
    }
}
