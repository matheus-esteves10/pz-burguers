package br.com.PzBurguer.emailMs.service;

import br.com.PzBurguer.emailMs.model.EmailModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(EmailModel emailModel) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());

        mailSender.send(message);
    }
}
