package com.ffs.api.config;

import com.ffs.api.notification.Notifier;
import com.ffs.api.service.CustumerActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author francisco
 */
@Configuration
public class ServiceConfig {

    @Bean
    public CustumerActivationService activationService(final Notifier notifier) {
        return new CustumerActivationService(notifier);
    }
}
