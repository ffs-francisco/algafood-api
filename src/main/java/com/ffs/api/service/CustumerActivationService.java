package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class CustumerActivationService {

    private final Notifier notifier;

    public CustumerActivationService(
            @Qualifier("URGENT")
            @Autowired Notifier notifiers
    ) {
        this.notifier = notifiers;
    }

    public void activate(final Custumer custumer) {
        custumer.activate();

        notifier.notify(custumer, "Seu cadastro está ativo!");
    }
}
