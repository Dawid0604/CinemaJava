/* Copyright 2026 CinemaJava */
package pl.dawid0604.cinemajava.domain.common;

public sealed interface Result<T> {

    record Success<T>(T value) implements Result<T> {}

    record Failure<T>(Problem problem) implements Result<T> {}

    record Problem(ProblemCode code, String message) {
        public static Problem conflict(final String message) {
            return new Problem(ProblemCode.CONFLICT, message);
        }

        public static Problem notFound(final String message) {
            return new Problem(ProblemCode.NOT_FOUND, message);
        }

        public static Problem validation(final String message) {
            return new Problem(ProblemCode.VALIDATION_ERROR, message);
        }
    }
}
