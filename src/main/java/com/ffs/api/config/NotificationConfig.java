package com.ffs.api.config;

import com.ffs.api.notification.EmailNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author francisco
 */
@Configuration
public class NotificationConfig {

    @Bean
    public EmailNotifier notifierEmail() {
        var notifier = new EmailNotifier("smpt.algafood.com.br");
        notifier.setUpperCase(true);

        return notifier;
    }
}
