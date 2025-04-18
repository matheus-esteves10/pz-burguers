package br.com.fiap.PzBurguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailRemetente;

    @Autowired
    private JavaMailSender javaMailSender;

    public String enviarEmail(String destinatario, String assunto, String mensagem) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailRemetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            System.out.println("email enviado");
            return "Email enviado com sucesso";
        } catch (Exception e) {
            return "Erro ao enviar email: " + e.getLocalizedMessage();
        }
    }
}
