package uk.co.jasonmarston.kiunzi.utility.validator;

import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorUtilitiesTest {
    @Test
    void invariantValidationShouldExposeConstraintFailures() {
        DomainInvariantViolationException ex = assertThrows(
            DomainInvariantViolationException.class,
            () -> InvariantValidation.INSTANCE.validate(new InvalidPayload())
        );

        assertEquals(1, ex.getViolations().size());
    }

    @Test
    void invariantValidationShouldAllowValidPayloads() {
        assertDoesNotThrow(() -> InvariantValidation.INSTANCE.validate(new ValidPayload()));
    }

    private static final class InvalidPayload {
        @NotBlank
        private final String value = "";
    }

    private static final class ValidPayload {
        @NotBlank
        private final String value = "value";
    }
}

