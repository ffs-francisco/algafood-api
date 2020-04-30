package com.ffs.api.publisher.listener;

import com.ffs.api.notification.Notifier;
import com.ffs.api.notification.type.NotifierType;
import com.ffs.api.publisher.event.CustumerActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.ffs.api.notification.type.UrgencyLevel.NORMAL;

/**
 *
 * @author francisco
 */
@Component
public class CustumerListener {

    @Autowired
    @NotifierType(NORMAL)
    private Notifier notifier;

    @EventListener
    public void notifyListener(final CustumerActivatedEvent event) {
        notifier.notify(event.getCustumer(), "Seu cadastro está ativo!");
    }

}
