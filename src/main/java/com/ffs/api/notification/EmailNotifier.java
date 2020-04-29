package com.ffs.api.notification;

import com.ffs.api.model.Custumer;

/**
 *
 * @author francisco
 */
public class EmailNotifier implements Notifier {

    private boolean upperCase;
    private final String smptServer;

    public EmailNotifier(String smptServer) {
        this.smptServer = smptServer;
    }

    @Override
    public void notify(final Custumer custumer, String message) {
        if (this.upperCase) {
            message = message.toUpperCase();
        }

        System.out.printf("Notificando %s através do e-mail %s usando o SMTP %s: %s\n",
                custumer.getName(), custumer.getEmail(), this.smptServer, message);
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

}
