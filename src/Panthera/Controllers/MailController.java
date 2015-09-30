package Panthera.Controllers;

import Panthera.Models.Email;
import Panthera.Services.MailService;
import Panthera.Services.Strategies.Mail.SendgridMailStrategy;
import Panthera.Views.MailListView;
import javafx.scene.Node;

public class MailController extends Controller {

    private final MailService mailService;

    public MailController() {
        this.mailService = new MailService(new SendgridMailStrategy());
        this.view = new MailListView(this, mailService);
    }

    public void cmdSendDankwoord() {
        Email email = new Email();
        email.setTo("d.rosbergen@gmail.com");
        email.setFrom("daan@daanrosbergen.nl");
        email.setSubject("Java mail test");
        email.setText("Hoi dit is Daan!");
        email.addAttachment("daan.test", "daantestattachment.txt");
        mailService.send(email);
        System.out.println("Email send!");
    }
}
