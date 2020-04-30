package com.ffs.api.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author francisco
 */
@Component
@ConfigurationProperties("notifier.email")
public class EmailNotifierConfig {

    /**
     * Email server url.
     */
    private String serverUrl;

    /**
     * Email server port. Deafult value 25
     */
    private Integer severPort = 25;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Integer getSeverPort() {
        return severPort;
    }

    public void setSeverPort(Integer severPort) {
        this.severPort = severPort;
    }

}
