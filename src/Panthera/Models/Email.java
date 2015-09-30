package Panthera.Models;

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

public class Email {

    public Email() {

    }

    public void setTo(ArrayList<String> recipients) {}
    public void setTo(String to) {}
    public void addTo(String to) {}

    public void setFrom(String from) {}

    public void setText(String text) {}
    public void setContent(String content) {}

    public void addAttachment(File file) {}
    public void addAttachment(InputStream inputStream) {}

}
