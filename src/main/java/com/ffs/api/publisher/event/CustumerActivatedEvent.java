package com.ffs.api.publisher.event;

import com.ffs.api.model.Custumer;

/**
 *
 * @author francisco
 */
public class CustumerActivatedEvent {

    private final Custumer custumer;

    public CustumerActivatedEvent(Custumer custumer) {
        this.custumer = custumer;
    }

    public Custumer getCustumer() {
        return custumer;
    }

}
