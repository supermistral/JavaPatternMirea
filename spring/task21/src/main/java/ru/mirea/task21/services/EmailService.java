package ru.mirea.task21.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String EMAIL_FROM;
    private final String objectInfoSubject = "Object creating notification";
    private final String htmlObjectInfoTemplate =
            "<!doctype html>" +
            "<html lang=\"en\">" +
            "<head><meta charset=\"utf-8\"></head>" +
            "<body>" +
            "<h1>Object Notification</h1>" +
            "<h2>--> %s" +
            "<h3>--> <i>%s</i></h3>" +
            "<div>Object has been created</div>" +
            "</body></html>";

    public EmailService(@Value("${spring.mail.from}") String emailFrom) {
        EMAIL_FROM = emailFrom;
    }

    @Async
    public void sendObjectInfoMail(String email, Object o) {
        sendMail(email, objectInfoSubject, String.format(htmlObjectInfoTemplate, o, o.getClass()));
    }

    @Async
    public void sendMail(String email, String subject, String message) {
        log.info("Sending mail to {}", email);

        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            mailMessage.setSubject(subject, "UTF-8");

            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setFrom(EMAIL_FROM);
            helper.setText(message, true);

            mailSender.send(mailMessage);
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }

    }
}
