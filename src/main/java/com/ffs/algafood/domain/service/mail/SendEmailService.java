package com.ffs.algafood.domain.service.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

public interface SendEmailService {

    void send(final Message message);

    @Getter
    @Builder
    class Message {

        @Singular
        private final Set<String> recipients;
        @NonNull
        private final String subject;
        @NonNull
        private final String body;
    }
}
