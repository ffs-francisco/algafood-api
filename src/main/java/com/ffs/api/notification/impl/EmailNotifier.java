package com.ffs.api.notification.impl;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.type.NotifierType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.NORMAL;

/**
 *
 * @author francisco
 */
@Component
@NotifierType(NORMAL)
public class EmailNotifier implements Notifier {

    @Value("${notifier.email-server-url}")
    private String emailHost;
    @Value("${notifier.email-server-port}")
    private Integer emailPort;

    @Override
    public void notify(final Custumer custumer, final String message) {
        System.out.println("e-mail host " + emailHost);
        System.out.println("e-mail port " + emailPort);

        System.out.printf("Notificando %s através do e-mail %s : %s\n", custumer.getName(), custumer.getEmail(), message);
    }

}
