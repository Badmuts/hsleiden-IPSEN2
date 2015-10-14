package Panthera.Controllers;

import Panthera.Models.Debiteur;
import Panthera.Models.Email;
import Panthera.Panthera;
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
        this.mainController.setSubview(new MailDankwoordView(this));
    }

    public void cmdShowUitnodigingView() {
        this.mainController.setSubview(new MailUitnodigingView(this));
    }

    public void cmdShowSelectRecipients(String onderwerp, String bericht) {
        this.mainController.setSubview(new MailSelectRecipientsView(this, onderwerp, bericht));
    }

    public void cmdSendDankwoord(ObservableList<Debiteur> debiteuren, String onderwerp, String bericht) {
        for (Debiteur debiteur: debiteuren) {
            Email email = new Email();
            email.setSubject(onderwerp);
            email.setFrom(Panthera.getInstance().getSetting().getMailadres());
            Parser parser = new DebiteurParser(debiteur);
            email.setText(parser.parse(bericht, debiteur));
            if (debiteur.isActive())
                email.addTo(debiteur.getEmail());
            mailService.send(email);
        }
        this.mainController.setSubview(new MailListView(this));
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
        this.mainController.setSubview(new MailListView(this));
    }

    @Override
    public void show() {
        this.mainController.setSubview(new MailListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }
}
