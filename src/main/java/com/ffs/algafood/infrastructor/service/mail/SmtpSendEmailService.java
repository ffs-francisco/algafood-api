package com.ffs.algafood.infrastructor.service.mail;

import com.ffs.algafood.core.EmailProperties;
import com.ffs.algafood.domain.service.mail.SendEmailService;
import com.ffs.algafood.infrastructor.service.mail.exception.EmailException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
@Profile("prod")
@AllArgsConstructor
public class SmtpSendEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties properties;
    private final Configuration configuration;

    @Override
    public void send(final Message message) {
        final var body = this.processTemplate(message);
        final var mimeMessage = mailSender.createMimeMessage();

        try {
            final var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setTo(message.getRecipients().toArray(new String[0]));
            helper.setFrom(properties.getSender());
            helper.setSubject(message.getSubject());
            helper.setText(body, true);

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new EmailException("Unable to send e-amil", ex);
        }
    }

    protected String processTemplate(final Message message) {
        try {
            final var template = configuration.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (IOException | TemplateException ex) {
            throw new EmailException("Unable to mount the template of the email", ex);
        }
    }
}
