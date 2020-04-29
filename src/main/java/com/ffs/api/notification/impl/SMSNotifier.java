package com.ffs.api.notification.impl;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.type.NotifierType;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.NORMAL;

/**
 *
 * @author francisco
 */
@Component
@NotifierType(NORMAL)
public class SMSNotifier implements Notifier {

    @Override
    public void notify(final Custumer custumer, final String message) {
        System.out.printf("Notificando %s por SMS através do telefone %s : %s\n",
                custumer.getName(), custumer.getTelephone(), message);
    }

}
