package uk.co.jasonmarston.kiunzi.utility.domain.exception;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class DomainExceptionConstructorsTest {

    @Test
    void dataIntegrityViolationMessageConstructorShouldSetMessage() {
        assertMessageOnly(DataIntegrityViolationException::new);
    }

    @Test
    void dataIntegrityViolationMessageAndCauseConstructorShouldSetFields() {
        assertMessageAndCause(DataIntegrityViolationException::new);
    }

    @Test
    void domainValidationMessageConstructorShouldSetMessage() {
        assertMessageOnly(DomainValidationException::new);
    }

    @Test
    void domainValidationMessageAndCauseConstructorShouldSetFields() {
        assertMessageAndCause(DomainValidationException::new);
    }

    @Test
    void notFoundMessageConstructorShouldSetMessage() {
        assertMessageOnly(NotFoundException::new);
    }

    @Test
    void notFoundMessageAndCauseConstructorShouldSetFields() {
        assertMessageAndCause(NotFoundException::new);
    }

    @Test
    void persistenceMessageConstructorShouldSetMessage() {
        assertMessageOnly(PersistenceException::new);
    }

    @Test
    void persistenceMessageAndCauseConstructorShouldSetFields() {
        assertMessageAndCause(PersistenceException::new);
    }

    @Test
    void versionMismatchMessageConstructorShouldSetMessage() {
        assertMessageOnly(VersionMismatchException::new);
    }

    @Test
    void versionMismatchMessageAndCauseConstructorShouldSetFields() {
        assertMessageAndCause(VersionMismatchException::new);
    }

    private static <T extends RuntimeException> void assertMessageOnly(
            final Function<String, T> constructor
    ) {
        final String message = "detail message";

        T ex = constructor.apply(message);

        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause());
    }

    private static <T extends RuntimeException> void assertMessageAndCause(
            final BiFunction<String, Throwable, T> constructor
    ) {
        final String message = "detail message";
        final Throwable cause = new IllegalStateException("root cause");

        T ex = constructor.apply(message, cause);

        assertEquals(message, ex.getMessage());
        assertSame(cause, ex.getCause());
    }
}

