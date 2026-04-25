package uk.co.jasonmarston.kiunzi.utility.domain.exception;

/**
 * Base class for all domain-layer exceptions.
 *
 * <p>Domain exceptions are unchecked ({@link RuntimeException}) and represent error
 * conditions that originate within the domain model. Concrete subclasses such as
 * {@link DomainValidationException} and {@link NotFoundException} refine the
 * failure semantics.</p>
 *
 * @see DomainValidationException
 * @see NotFoundException
 * @see PersistenceException
 * @see DataIntegrityViolationException
 * @see VersionMismatchException
 */
public abstract class DomainException extends RuntimeException {

    protected DomainException(
            final String message
    ) {
        super(message);
    }

    protected DomainException(
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
    }
}
