package community.notificationserver.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final String EMAIL_TEMPLATE = "Hello %s, your notification message here.";

    private final JavaMailSender mailSender;

    public EmailService(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String recipientMail, final String recipientName, final String subject) throws MessagingException {
        String emailContent = String.format(EMAIL_TEMPLATE, recipientName);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientMail);
        helper.setSubject(subject);
        helper.setText(emailContent);
        mailSender.send(message);
    }
}
