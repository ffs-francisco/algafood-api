package com.ffs.api.notification.impl;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.config.EmailNotifierConfig;
import com.ffs.api.notification.type.NotifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.NORMAL;

/**
 *
 * @author francisco
 */
@Component
@NotifierType(NORMAL)
public class EmailNotifier implements Notifier {

    @Autowired
    private EmailNotifierConfig notifierConfig;

    @Override
    public void notify(final Custumer custumer, final String message) {
        System.out.println("e-mail host " + notifierConfig.getServerUrl());
        System.out.println("e-mail port " + notifierConfig.getSeverPort());

        System.out.printf("Notificando %s através do e-mail %s : %s\n", custumer.getName(), custumer.getEmail(), message);
    }

}
