package com.ffs.algafood.infrastructor.service;

import com.ffs.algafood.core.EmailProperties;
import com.ffs.algafood.domain.service.mail.SendEmailService;
import com.ffs.algafood.infrastructor.service.storage.exception.EmailException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmtpSendEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties properties;

    @Override
    public void send(final Message message) {
        try {
            final var mimeMessage = mailSender.createMimeMessage();

            final var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setTo(message.getRecipients().toArray(new String[0]));
            helper.setFrom(properties.getSender());
            helper.setSubject(message.getSubject());
            helper.setText(message.getBody(), true);

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new EmailException("Unable to send e-amil", ex);
        }
    }
}
