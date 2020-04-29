package com.ffs.api.notification;

import com.ffs.api.model.Custumer;

/**
 *
 * @author francisco
 */
public interface Notifier {

    void notify(final Custumer custumer, final String message);
}
