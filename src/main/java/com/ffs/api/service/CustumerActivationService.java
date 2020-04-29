package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.notification.Notifier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class CustumerActivationService {

    private final List<Notifier> notifiers;

    public CustumerActivationService(
            @Autowired List<Notifier> notifiers
    ) {
        this.notifiers = notifiers;
    }

    public void activate(final Custumer custumer) {
        custumer.activate();

        if (notifiers.isEmpty()) {
            System.out.println("Não existe notificador, mas o cliente fora ativado!");
        } else {
            notifiers.forEach(notifier -> notifier.notify(custumer, "Seu cadastro está ativo!"));
        }
    }
}
