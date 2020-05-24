package com.ffs.algafood.core.validation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author francisco
 */
@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validatorFactory(final MessageSource messageSource) {
        final var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
