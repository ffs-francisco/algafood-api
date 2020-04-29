package com.ffs.api.notification.impl;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.type.NotifierType;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.URGENT;

/**
 *
 * @author francisco
 */
@Component
@NotifierType(URGENT)
public class EmailNotifier implements Notifier {

    @Override
    public void notify(final Custumer custumer, final String message) {
        System.out.printf("Notificando %s através do e-mail %s : %s\n",
                custumer.getName(), custumer.getEmail(), message);
    }

}
