package org.shriniwas.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String body, String attachmentPath) {

        if(!Boolean.parseBoolean(ConfigReader.get("email.enabled"))) {
            return;
        }

        try {

            String host = ConfigReader.get("email.smtp.host");
            String port = ConfigReader.get("email.smtp.port");
            String username = ConfigReader.get("email.username");
            String password = ConfigReader.get("email.password");

            String to = ConfigReader.get("email.to");
            String cc = ConfigReader.get("email.cc");
            String subject = ConfigReader.get("project.name")+ "-" + ConfigReader.get("env").toUpperCase() +" "+ ConfigReader.get("email.subject") +" "+ DateUtils.getCurrentDateTime().toString();

            Properties props = new Properties();

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");


            Session session = Session.getInstance(
                    props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            if (cc != null && !cc.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(cc));
            }

            message.setSubject(subject);

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            if (attachmentPath != null) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(attachmentPath));
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}