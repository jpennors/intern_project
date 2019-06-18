/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Josselin
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public static void send(String new_password, String new_email) {

        final String username = "sr03.intern.project@gmail.com";
        final String password = "dsokvdf6(\"dfs9";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sr03.intern.project@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(new_email)
            );
            message.setSubject("Site Stagiaire : Création d'un compte");
            message.setText("Hey,"
                    + "\n\n Bienvenue sur notre site de stagiaire"
                    + "\n\n Voici tes identifiants"
                    + "\n Email: " + new_email
                    + "\n Mot de passe " + new_password
                    + "\n\nA très vite!"
            );

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}