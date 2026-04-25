/**
 * Defines validation utilities and exception types used for domain invariant checks.
 */
module utility.validator {
    requires static lombok;

    requires utility.domain.exception;

    requires jakarta.validation;

    exports uk.co.jasonmarston.kiunzi.utility.validator;
}