package com.ffs.algafood.infrastructor.service.mail;

import com.ffs.algafood.core.EmailProperties;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("default")
public class FakeSendEmailService extends SmtpSendEmailService {

    public FakeSendEmailService(
            final JavaMailSender mailSender,
            final EmailProperties properties,
            final Configuration configuration
    ) {
        super(mailSender, properties, configuration);
    }

    @Override
    public void send(final Message message) {
        log.info("[FAKE-MAIL] To: {}\n{}", message.getRecipients(), this.processTemplate(message));
    }
}
