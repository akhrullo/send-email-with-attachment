package uz.iDev.service;

import org.springframework.stereotype.Component;
import uz.iDev.model.Email;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public interface EmailService   {

    Boolean sendSimpleMessage(Email email);

    Boolean sendMessageWithCC(Email email);

    Boolean sendMessageWithAttachment(Email email) throws IOException, MessagingException;
}
