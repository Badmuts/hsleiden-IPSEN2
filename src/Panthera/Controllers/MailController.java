package Panthera.Controllers;

import Panthera.Models.Debiteur;
import Panthera.Models.Email;
import Panthera.Services.Decorators.DebiteurParser;
import Panthera.Services.Decorators.Parser;
import Panthera.Services.MailService;
import Panthera.Views.MailDankwoordView;
import Panthera.Views.MailListView;
import Panthera.Views.MailSelectRecipientsView;
import Panthera.Views.MailUitnodigingView;
import javafx.collections.ObservableList;

public class MailController extends Controller {

    private final MailService mailService;
    private final MainController mainController;

    public MailController(MainController mainController) {
        this.mainController = mainController;
        this.mailService = new MailService();
    }

    public void cmdShowDankwoordView() {
        this.view = new MailDankwoordView(this);
        show();
    }

    public void cmdShowUitnodigingView() {
        this.view = new MailUitnodigingView(this);
        show();
    }

    public void cmdShowSelectRecipients(String onderwerp, String bericht) {
        this.view = new MailSelectRecipientsView(this, onderwerp, bericht);
        show();
    }

    public void cmdSendDankwoord(ObservableList<Debiteur> debiteuren, String onderwerp, String bericht) {
        for (Debiteur debiteur: debiteuren) {
            Email email = new Email();
            email.setSubject(onderwerp);
            email.setFrom("d.rosbergen@gmail.com");
            Parser parser = new DebiteurParser(debiteur);
            email.setText(parser.parse(bericht, debiteur));
            if (debiteur.isActive())
                email.addTo(debiteur.getEmail());
            mailService.send(email);
        }
        this.view = new MailListView(this);
        show();
    }

    public void cmdSendUitnodiging(ObservableList<Debiteur> debiteuren, String onderwerp, String bericht) {
        for (Debiteur debiteur: debiteuren) {
            Email email = new Email();
            email.setSubject(onderwerp);
            email.setFrom("brandonvanwijk@gmail.com");
            Parser parser = new DebiteurParser(debiteur);
            email.setText(parser.parse(bericht, debiteur));
            if (debiteur.isActive())
                email.addTo(debiteur.getEmail());
            mailService.send(email);
        }
        this.view = new MailListView(this);
        show();
    }

    @Override
    public void show() {
        this.mainController.setSubview(new MailListView(this));
    }
}
