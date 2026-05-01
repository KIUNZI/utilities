/**
 * Provides shared annotations and wiring dependencies used across adapters and ports.
 */
module utility.producer {
    requires utility.validator;

    requires jakarta.annotation;
    requires jakarta.cdi;
    requires jakarta.validation;
    requires org.jboss.logging;
    requires org.modelmapper;
    requires org.modelmapper.module.record;
    requires io.quarkus.core;

    exports uk.co.jasonmarston.kiunzi.utility.producer.annotation;
}