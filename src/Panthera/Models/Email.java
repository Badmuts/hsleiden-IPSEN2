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
import java.util.Calendar;
import java.util.Properties;

public class Email {

    private ArrayList<String> recipients;
    private String from;
    private String content;
    private ArrayList<ArrayList<String>> attachments;
    private String contentType;
    private String subject;

    public Email() {
        this.recipients = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.contentType = "text/plain";
    }

    public void setTo(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    public void setTo(String to) {
        this.recipients.clear();
        this.recipients.add(to);
    }

    public void addTo(String to) {
        this.recipients.add(to);
    }

    public void addTo(ArrayList<String> recipients) {
        this.recipients.addAll(recipients);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setText(String text) {
        this.content = text;
    }

    public void setContent(String content, String type) {
        this.content = content;
        this.contentType = type;
    }

    public void addAttachment(String filelocation, String filename) {
        ArrayList<String> attachmentData = new ArrayList<>();
        attachmentData.add(filelocation);
        attachmentData.add(filename);
        this.attachments.add(attachmentData);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<ArrayList<String>> getAttachments() {
        return attachments;
    }

    public String getContentType() {
        return contentType;
    }

    public String getSubject() {
        return subject;
    }
}
