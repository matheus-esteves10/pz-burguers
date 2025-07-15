package br.com.PzBurguer.emailMs.service;

import br.com.PzBurguer.emailMs.model.EmailModel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public void sendWelcome(EmailModel emailModel) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());

        mailSender.send(message);
    }

    public void sendNotaFiscal(EmailModel emailModel) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(emailFrom);
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(), false);

            if (emailModel.getAnexo() != null && emailModel.getNomeArquivo() != null) {
                helper.addAttachment(emailModel.getNomeArquivo(), new ByteArrayResource(emailModel.getAnexo()));
            }

            mailSender.send(mimeMessage);
            System.out.println("📧 Nota fiscal enviada para: " + emailModel.getEmailTo());

        } catch (Exception e) {
            System.out.println("❌ Erro ao enviar nota fiscal: " + e.getMessage());
        }
    }
}
