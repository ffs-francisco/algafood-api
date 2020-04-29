package com.ffs.api.notification;

import com.ffs.api.model.Custumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
@Qualifier("URGENT")
public class SMSNotifier implements Notifier {

    @Override
    public void notify(final Custumer custumer, final String message) {
        System.out.printf("Notificando %s por SMS através do telefone %s : %s\n",
                custumer.getName(), custumer.getTelephone(), message);
    }

}
