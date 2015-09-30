package Panthera.Services.Strategies.Mail;

import Panthera.Models.Email;
import Panthera.Services.Interfaces.MailStrategy;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class JavaMailStrategy implements MailStrategy {

    private String smtpServer;
    private String username;
    private String password;
    private ArrayList<String> recipients = new ArrayList<>();
    private String from;
    private String content;
    private ArrayList<File> attachments = new ArrayList<>();

    public JavaMailStrategy() {

        // Assuming you are sending email from localhost
        this.host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>This is actual message</h1>", "text/html" );

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override public void setSMTPServer(String smtpServer) {

    }

    @Override public void setUsername(String username) {

    }

    @Override public void setPassword(String password) {

    }

    @Override public void send(Email email) {

    }
}
