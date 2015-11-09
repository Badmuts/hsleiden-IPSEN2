package Panthera.Models;

import java.util.ArrayList;

/**
 * Model representing a email. Used be the MailService and its strategies.
 *
 * @author Daan Rosbergen
 */
public class Email extends Model {

    private ArrayList<String> recipients;
    private String from;
    private String content;
    private ArrayList<ArrayList<String>> attachments;
    private String contentType;
    private String subject;

    /**
     * Contstructor sets sane defaults.
     */
    public Email() {
        this.recipients = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.contentType = "text/plain";
    }

    /**
     * Set recipients via a List.
     *
     * @param recipients
     */
    public void setTo(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * Set recipient.
     *
     * @param to
     */
    public void setTo(String to) {
        this.recipients.clear();
        this.recipients.add(to);
    }

    /**
     * Add recipient to list of recipients.
     *
     * @param to
     */
    public void addTo(String to) {
        this.recipients.add(to);
    }

    /**
     * Add list of recipients to list of recipients.
     *
     * @param recipients
     */
    public void addTo(ArrayList<String> recipients) {
        this.recipients.addAll(recipients);
    }

    /**
     * Set from.
     *
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Set message text.
     *
     * @param text
     */
    public void setText(String text) {
        this.content = text;
    }

    /**
     * Set content and its type to the email. This makes sending HTML emails possible.
     *
     * @param content   String  Content (plain text or HTML etc.).
     * @param type      String  Valid MIME type.
     */
    public void setContent(String content, String type) {
        this.content = content;
        this.contentType = type;
    }

    /**
     * Add attachment to list of attachments via filelocation and filename used in the email.
     *
     * @param filelocation
     * @param filename
     */
    public void addAttachment(String filelocation, String filename) {
        ArrayList<String> attachmentData = new ArrayList<>();
        attachmentData.add(filelocation);
        attachmentData.add(filename);
        this.attachments.add(attachmentData);
    }

    /**
     * Set subject of the email.
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Retrieve recipients.
     *
     * @return
     */
    public ArrayList<String> getRecipients() {
        return recipients;
    }

    /**
     * Get from.
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get content of the email.
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * Get all attachments of the email.
     *
     * @return
     */
    public ArrayList<ArrayList<String>> getAttachments() {
        return attachments;
    }

    /**
     * Get content type of the email.
     *
     * @return
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Get subject of the email.
     * 
     * @return
     */
    public String getSubject() {
        return subject;
    }
}
