package uk.co.jasonmarston.kiunzi.utility.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvariantUtilitiesTest {

    @Test
    void preconditionsShouldRejectNull() {
        NullPointerException ex = assertThrows(
            NullPointerException.class,
            () -> Preconditions.requireNonNull(null, "value must not be null")
        );

        assertEquals("value must not be null", ex.getMessage());
    }

    @Test
    void preconditionsShouldAllowNonNull() {
        assertDoesNotThrow(() -> Preconditions.requireNonNull("ok", "value must not be null"));
    }

    @Test
    void invariantsShouldRejectNonNull() {
        IllegalStateException ex = assertThrows(
            IllegalStateException.class,
            () -> Invariants.requireNull("already-set", "must be null")
        );

        assertEquals("must be null", ex.getMessage());
    }

    @Test
    void invariantsShouldAllowNull() {
        assertDoesNotThrow(() -> Invariants.requireNull(null, "must be null"));
    }
}

