package com.ffs.api.service;

import com.ffs.api.model.Custumer;
import com.ffs.api.publisher.event.CustumerActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
public class CustumerActivationService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void activate(final Custumer custumer) {
        custumer.activate();

        eventPublisher.publishEvent(new CustumerActivatedEvent(custumer));
    }
}
