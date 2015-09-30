package Panthera.Services;

import Panthera.Models.Email;
import Panthera.Services.Interfaces.MailStrategy;
import Panthera.Services.Strategies.Mail.JavaMailStrategy;

public class MailService {

    private MailStrategy mailStrategy;

    public MailService() {
        this.mailStrategy = new JavaMailStrategy();
    }

    public MailService(MailStrategy mailStrategy) {
        this.mailStrategy = mailStrategy;
    }

    public void setMailStrategy(MailStrategy mailStrategy) {
        this.mailStrategy = mailStrategy;
    }

    public void send(Email email) {
        this.mailStrategy.send(email);
    }

}
