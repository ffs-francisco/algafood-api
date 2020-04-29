package com.ffs.api.notification.type;

import java.lang.annotation.Retention;
import org.springframework.beans.factory.annotation.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author francisco
 */
@Qualifier
@Retention(RUNTIME)
public @interface NotifierType {

    UrgencyLevel value();
}
