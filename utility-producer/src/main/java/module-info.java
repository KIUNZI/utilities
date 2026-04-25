/**
 * Provides shared annotations and wiring dependencies used across adapters and ports.
 */
module utility.producer {
    requires utility.validator;

    requires jakarta.cdi;
    requires jakarta.validation;
    requires org.modelmapper;
    requires org.modelmapper.module.record;

    exports uk.co.jasonmarston.kiunzi.utility.producer.annotation;
}