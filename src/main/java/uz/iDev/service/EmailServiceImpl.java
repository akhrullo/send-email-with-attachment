package uz.iDev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import uz.iDev.model.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender eMailSender;

    public EmailServiceImpl(JavaMailSender eMailSender) {
        this.eMailSender = eMailSender;
    }

    @Override
    public Boolean sendSimpleMessage(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getRecipients().stream().collect(Collectors.joining(",")).split(","));
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        boolean isSent = false;

        try {
            eMailSender.send(message);
            isSent = true;
        }catch (Exception e) {
            LOGGER.error("Sending email error: {%s}".formatted(e.getMessage()));
        }

        return isSent;
    }

    @Override
    public Boolean sendMessageWithCC(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getRecipients().stream().collect(Collectors.joining(",")));
        message.setCc(email.getCcList().stream().collect(Collectors.joining(",")));
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        message.setFrom("James Gosling");

        boolean isSent = false;

        try {
            eMailSender.send(message);
            isSent = true;
        }catch (Exception e) {
            LOGGER.error("Sending email error: {%s}".formatted(e.getMessage()));
        }

        return isSent;
    }

    @Override
    public Boolean sendMessageWithAttachment(Email email) throws MessagingException, IOException {
        MimeMessage message = eMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo(email.getRecipients().stream().collect(Collectors.joining(",")).split(","));
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(email.getBody());

        Resource resource = new ClassPathResource(email.getAttachmentPath());
        mimeMessageHelper.addAttachment("attachment", resource.getFile());

        boolean isSent = false;

        try {
            eMailSender.send(message);
            isSent = true;
        }catch (Exception e) {
            LOGGER.error("Sending email error: {%s}".formatted(e.getMessage()));
        }

        return isSent;
    }
}
