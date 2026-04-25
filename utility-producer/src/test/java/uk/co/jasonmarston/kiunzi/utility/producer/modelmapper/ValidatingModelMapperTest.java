package uk.co.jasonmarston.kiunzi.utility.producer.modelmapper;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.jasonmarston.kiunzi.utility.validator.DomainInvariantViolationException;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatingModelMapperTest {
    private ValidatingModelMapper mapper;

    @BeforeEach
    void setUp() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        mapper = new ValidatingModelMapper(validator);
    }

    @Test
    void mapShouldReturnMappedObjectWhenValid() {
        Destination destination = mapper.map(new Source("Interstellar"), Destination.class);

        assertEquals("Interstellar", destination.getTitle());
    }

    @Test
    void mapShouldThrowDomainViolationExceptionWhenConstraintsFail() {
        DomainInvariantViolationException exception = assertThrows(
            DomainInvariantViolationException.class,
            () -> mapper.map(new Source(null), Destination.class)
        );

        assertFalse(exception.getViolations().isEmpty());
        assertTrue(exception.getMessage().contains("title"));
    }

    @Test
    void mapIntoExistingDestinationShouldValidateFinalState() {
        Destination destination = new Destination("Existing title");

        assertThrows(
            DomainInvariantViolationException.class,
            () -> mapper.map(new Source(null), destination)
        );
    }

    @Test
    void mapIntoExistingDestinationShouldUpdateWhenValid() {
        Destination destination = new Destination("Existing title");

        mapper.map(new Source("Updated title"), destination);

        assertEquals("Updated title", destination.getTitle());
    }

    @Test
    void mapUsingTypeShouldReturnMappedObjectWhenValid() {
        Type destinationType = Destination.class;

        Destination destination = mapper.map(new Source("Arrival"), destinationType);

        assertEquals("Arrival", destination.getTitle());
    }

    @Test
    void mapUsingTypeShouldThrowDomainViolationExceptionWhenConstraintsFail() {
        Type destinationType = Destination.class;

        assertThrows(
            DomainInvariantViolationException.class,
            () -> mapper.map(new Source(null), destinationType)
        );
    }

    @Test
    void mapIntoExistingDestinationWithNamedTypeMapShouldValidateFinalState() {
        mapper.createTypeMap(Source.class, Destination.class, "namedDestinationMap");
        Destination destination = new Destination("Existing title");

        assertThrows(
            DomainInvariantViolationException.class,
            () -> mapper.map(new Source(null), destination, "namedDestinationMap")
        );
    }

    @Test
    void mapUsingClassAndNamedTypeMapShouldReturnMappedObjectWhenValid() {
        mapper.createTypeMap(Source.class, Destination.class, "namedClassMap");

        Destination destination = mapper.map(
            new Source("Blade Runner"),
            Destination.class,
            "namedClassMap"
        );

        assertEquals("Blade Runner", destination.getTitle());
    }

    @Test
    void mapUsingTypeAndNamedTypeMapShouldThrowDomainViolationExceptionWhenConstraintsFail() {
        mapper.createTypeMap(Source.class, Destination.class, "namedTypeMap");
        Type destinationType = Destination.class;

        assertThrows(
            DomainInvariantViolationException.class,
            () -> mapper.map(new Source(null), destinationType, "namedTypeMap")
        );
    }

    private static final class Source {
        private final String title;

        private Source(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    private static final class Destination {
        @NotBlank
        private String title;

        private Destination() {
        }

        private Destination(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

