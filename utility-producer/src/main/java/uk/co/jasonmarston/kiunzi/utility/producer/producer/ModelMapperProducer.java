package uk.co.jasonmarston.kiunzi.utility.producer.producer;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import org.jboss.logging.Logger;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.record.RecordModule;
import uk.co.jasonmarston.kiunzi.utility.producer.annotation.PersistenceAwareValidating;
import uk.co.jasonmarston.kiunzi.utility.producer.annotation.Validating;
import uk.co.jasonmarston.kiunzi.utility.producer.modelmapper.ValidatingModelMapper;

@ApplicationScoped
class ModelMapperProducer {
    private static final Logger LOG = Logger.getLogger(ModelMapperProducer.class);

    private final Validator validator;

    @Inject
    public ModelMapperProducer(final Validator validator) {
        this.validator = validator;
    }

    @PostConstruct
    void logInstantiation() {
        LOG.infof("%s instantiated", ModelMapperProducer.class.getSimpleName());
    }

    @Produces
    @Validating
    @ApplicationScoped
    public ModelMapper validatingModelMapper() {
        final ModelMapper modelMapper = new ValidatingModelMapper(validator);

        modelMapper
            .registerModule(new RecordModule())
            .getConfiguration()
            .setFieldMatchingEnabled(false)
            .setMethodAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }

    @Produces
    @PersistenceAwareValidating
    @ApplicationScoped
    public ModelMapper plainModelMapper() {
        final ModelMapper modelMapper = new ValidatingModelMapper(validator);

        modelMapper
            .registerModule(new RecordModule())
            .getConfiguration()
            .setFieldMatchingEnabled(false)
            .setMethodAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }
}
