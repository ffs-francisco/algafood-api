package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;

/**
 *
 * @author francisco
 */
public class CustumerActivationService {

    private final Notifier notifier;

    public CustumerActivationService(Notifier notification) {
        this.notifier = notification;
    }

    public void activate(final Custumer custumer) {
        custumer.activate();

        notifier.notify(custumer, "Seu cadastro está ativo");
    }
}
