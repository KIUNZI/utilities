package uk.co.jasonmarston.kiunzi.utility.producer.annotation;

import jakarta.inject.Qualifier;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapperQualifierAnnotationsTest {

    @Test
    void validatingShouldDeclareQualifierRetentionAndTarget() {
        assertQualifierMetadata(Validating.class);
    }

    @Test
    void persistenceAwareValidatingShouldDeclareQualifierRetentionAndTarget() {
        assertQualifierMetadata(PersistenceAwareValidating.class);
    }

    private static void assertQualifierMetadata(final Class<?> annotationType) {
        assertTrue(annotationType.isAnnotationPresent(Qualifier.class));

        Retention retention = annotationType.getAnnotation(Retention.class);
        assertNotNull(retention);
        assertEquals(RetentionPolicy.RUNTIME, retention.value());

        Target target = annotationType.getAnnotation(Target.class);
        assertNotNull(target);

        Set<ElementType> elementTypes = Set.of(target.value());
        assertEquals(Set.of(ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD), elementTypes);
    }
}

