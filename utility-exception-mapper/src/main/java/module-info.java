/**
 * Provides HTTP exception-mapping infrastructure.
 *
 * <p>This module contributes Jakarta REST {@code ExceptionMapper} implementations
 * and response payload models used to translate domain and framework failures into
 * structured API error responses.</p>
 */
module utility.exception.mapper {
    requires domain.exceptions;
    requires utility.validator;
    requires io.quarkus.security.api;
    requires io.smallrye.common.constraint;
    requires io.smallrye.mutiny;
    requires jakarta.cdi;
    requires jakarta.validation;
    requires jakarta.ws.rs;
    requires org.modelmapper;
    requires quarkus.core;
}