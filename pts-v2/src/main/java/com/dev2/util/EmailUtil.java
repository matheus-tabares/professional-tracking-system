/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.util;

import java.util.Properties;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bruno
 */
public class EmailUtil {

    private int id;
    private final String assunto = "[PTS] - Esqueci Minha Senha";
    private String conteudo;
    private String destinatario;
    private String senhaGerada;

    public String gerarSenha() {

        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        this.senhaGerada = myRandom.substring(0, 6);
        System.out.println("GEEROU A SENHA");
        return this.senhaGerada;
    }

    public String gerarDescricao(String senha) {
        StringBuilder stb = new StringBuilder();
        stb.append("Boa tarde, você solicitou uma nova senha! \n Sua senha agora é: ").append(senha);
        System.out.println("GEEROU A DESCRIÇÃO");
        this.conteudo = stb.toString();
        return stb.toString();
    }

    private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }

    public void enviarEmail() throws MessagingException {

        final String username = "eventosdev1@gmail.com";
        final String password = "evento2016";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Transport transport = session.getTransport();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.destinatario));
            message.setSubject(this.assunto);
            message.setText(this.conteudo);
            transport.connect();
            Transport.send(message, message.getRecipients(Message.RecipientType.TO));
            enviarMensagem(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso");
            System.out.println("EMAIL ENVIADO COM SUCESSO.");

            setConteudo("");
            setDestinatario("");
            transport.close();

        } catch (MessagingException ex) {
            enviarMensagem(FacesMessage.SEVERITY_INFO, "Erro ao tentar enviar E-mail");
            System.err.println("erro" + ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

}
