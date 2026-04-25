/**
 * Defines validation utilities and exception types used for domain invariant checks.
 */
module utility.validator {
    requires static lombok;

    requires domain.exceptions;

    requires jakarta.validation;

    exports uk.co.jasonmarston.kiunzi.utility.validator;
}