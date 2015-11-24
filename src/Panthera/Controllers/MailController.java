package Panthera.Controllers;

import Panthera.Models.Debiteur;
import Panthera.Models.Email;
import Panthera.Services.Decorators.DebiteurParser;
import Panthera.Services.Decorators.Parser;
import Panthera.Services.MailService;
import Panthera.Views.MailDankwoordView;
import Panthera.Views.MailHerinneringView;
import Panthera.Views.MailListView;
import Panthera.Views.MailSelectRecipientsView;
import Panthera.Views.MailUitnodigingView;
import javafx.collections.ObservableList;

/**
 * Controller used for interaction with Mail. Allows the user to send a 'Dankwoord' or 'Uitnodiging'
 * to multiple members.
 *
 * @author Daan Rosbergen
 */
public class MailController extends Controller {

    private final MailService mailService;
    private final MainController mainController;

    /**
     * Creates a MailService object used to send mail.
     *
     * @author Daan Rosbergen
     * @param mainController MainController used to set its subview.
     */
    public MailController(MainController mainController) {
        this.mainController = mainController;
        this.mailService = new MailService();
    }

    /**
     * Sets subview to MailDankwoordView.
     *
     * @author Daan Rosbergen
     */
    public void cmdShowDankwoordView() {
        this.mainController.setSubview(new MailDankwoordView(this));
    }

    /**
     * Sets subview to MailUitnodigingView.
     *
     * @author Daan Rosbergen
     */
    public void cmdShowUitnodigingView() {
        this.mainController.setSubview(new MailUitnodigingView(this));
    }

    /**
     * Sets subview to MailHerinneringView.
     *
     * @author Daan Rosbergen
     */
    public void cmdShowHerinneringView() {
    	this.mainController.setSubview(new MailHerinneringView(this));
	}

    /**
     * Sets subview to MailSelectRecipientsView and passes the current mail subject and mail body.
     *
     * @author Daan Rosbergen
     * @param onderwerp String  Mail subject.
     * @param bericht   String  Mail message.
     */
    public void cmdShowSelectRecipients(String onderwerp, String bericht) {
        this.mainController.setSubview(new MailSelectRecipientsView(this, onderwerp, bericht));
    }
    

    /**
     * Send a 'Dankwoord' to a list of debitors with a custom subject and mail body.
     *
     * @author Daan Rosbergen
     * @param debiteuren    ObservableList<Debiteur> List with debitors.
     * @param onderwerp     String                   Mail subject.
     * @param bericht       String                   Mail message.
     */
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
        this.mainController.setSubview(new MailListView(this));
    }

    /**
     * Send a 'Uitnodging' to a list of debitors with a custom subject and mail body.
     *
     * @author Daan Rosbergen
     * @param debiteuren    ObservableList<Debiteur> List with debitors.
     * @param onderwerp     String                   Mail subject.
     * @param bericht       String                   Mail message.
     */
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

    /**
     * Set default MailListView to the controller.
     *
     * @author Daan Rosbergen
     */
    @Override
    public void show() {
        this.mainController.setSubview(new MailListView(this));
    }

    /**
     * Retrieve the MainController.
     *
     * @author Daan Rosbergen
     * @return MainController MainController.
     */
    public MainController getMainController() {
        return mainController;
    }

}
