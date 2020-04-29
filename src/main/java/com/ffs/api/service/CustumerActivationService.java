package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.type.NotifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.NORMAL;

/**
 *
 * @author francisco
 */
@Component
public class CustumerActivationService {

    private final Notifier notifier;

    public CustumerActivationService(
            @NotifierType(NORMAL)
            @Autowired Notifier notifiers
    ) {
        this.notifier = notifiers;
    }

    public void activate(final Custumer custumer) {
        custumer.activate();

        notifier.notify(custumer, "Seu cadastro está ativo!");
    }
}
