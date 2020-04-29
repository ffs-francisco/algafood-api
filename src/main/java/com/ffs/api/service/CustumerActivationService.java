package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class CustumerActivationService {

    private final Notifier notifier;

    public CustumerActivationService(
            @Autowired(required = false) Notifier notification
    ) {
        this.notifier = notification;
    }

    public void activate(final Custumer custumer) {
        custumer.activate();

        if (notifier != null) {
            notifier.notify(custumer, "Seu cadastro está ativo!");
        } else {
            System.out.println("Não existe notificador, mas o cliente fora ativado!");
        }
    }
}
