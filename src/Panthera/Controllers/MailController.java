package Panthera.Controllers;

import Panthera.Models.Debiteur;
import Panthera.Models.Email;
import Panthera.Services.Decorators.DebiteurParser;
import Panthera.Services.Decorators.Parser;
import Panthera.Services.MailService;
import Panthera.Views.MailDankwoordView;
import Panthera.Views.MailListView;
import Panthera.Views.MailSelectRecipientsView;
import javafx.collections.ObservableList;

public class MailController extends Controller {

    private final MailService mailService;

    public MailController() {
        this.mailService = new MailService();
        this.view = new MailListView(this);
    }

    public void cmdShowDankwoordView() {
        this.view = new MailDankwoordView(this);
        show();
    }

    public void cmdShowSelectRecipients(String onderwerp, String bericht) {
        this.view = new MailSelectRecipientsView(this, onderwerp, bericht);
        show();
    }

    public void cmdSendDankwoord(ObservableList<Debiteur> debiteuren, String onderwerp, String bericht) {
        Email email = new Email();
        email.setSubject(onderwerp);
        email.setFrom("d.rosbergen@gmail.com");
        for (Debiteur debiteur: debiteuren) {
            Parser parser = new DebiteurParser(debiteur);
            email.setText(parser.parse(bericht, debiteur));
            if (debiteur.isActive())
                email.addTo(debiteur.getEmail());
        }
        mailService.send(email);
        this.view = new MailListView(this);
        show();
    }
}
