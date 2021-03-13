package com.ffs.algafood.infrastructor.service.mail;

import com.ffs.algafood.core.email.EmailProperties;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@Profile("dev")
public class SandBoxSendEmailService extends SmtpSendEmailService {

    private final EmailProperties properties;

    public SandBoxSendEmailService(
            final JavaMailSender mailSender,
            final EmailProperties properties,
            final Configuration configuration
    ) {
        super(mailSender, properties, configuration);
        this.properties = properties;
    }

    @Override
    protected MimeMessage createMimeMessage(final Message message) throws MessagingException {
        final var mimeMessage = super.createMimeMessage(message);
        final var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(properties.getSandbox().getRecipient());

        return mimeMessage;
    }
}
