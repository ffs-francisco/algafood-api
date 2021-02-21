package com.ffs.algafood.domain.service.mail;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface SendEmailService {

    void send(final Message message);

    @Getter
    @Builder
    class Message {

        private final Set<String> recipients;
        private final String subject;
        private final String body;
    }
}
