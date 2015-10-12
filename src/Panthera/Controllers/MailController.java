package Panthera.Controllers;

import java.util.List;

import javax.mail.MessagingException;

import Panthera.Models.Debiteur;
import Panthera.Models.Email;
import Panthera.Models.InkoopfactuurRegel;
import Panthera.Services.MailService;
import Panthera.Services.Decorators.DebiteurParser;
import Panthera.Services.Decorators.Parser;
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
            email.setFrom("d.rosbergen@gmail.com");
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
    
    /**
     * Send inkoopfactuur to given email.
     * @throws MessagingException 
     */
    public void cmdSendInkoopfactuur(List<InkoopfactuurRegel> inkoopfactuurRegels) throws MessagingException {
    	Email email = new Email();
    	email.setSubject("Inkoopfactuur");
    	email.setFrom("roytouw@hotmail.com");
    	email.addTo("brandonvanwijk@gmail.com");
    	email.setContent(createTable(inkoopfactuurRegels), "text/html");
    	mailService.send(email);
    }
    
    public String createTable(List<InkoopfactuurRegel> regels) {
    	String table = "<table>";
    	table += createHeader();
    	for(InkoopfactuurRegel regel : regels) {
    		table += (""
    				+ "<tr>"
    				+ "<td>" + regel.getProduct_id() + "</td>"
    				+ "<td>" + regel.getProductnaam() + "</td>"
    				+ "<td>" + regel.getAantal() + "</td>"
    				+ "<td>" + regel.getPrijs() + "</td>"
    				+ "</tr>"
    				+ "");
    	}
    	table += "</table>";
    	return table;
    }
    
    /**
     * create Inkoopfactuur table header in html format.
     * @return String header.
     */
    public String createHeader() {
    	String header = ("<tr>"
    			+ "<th>product_id</th>"
    			+ "<th>product_naam</th>"
    			+ "<th>aantal</th>"
    			+ "<th>prijs</th>"
    			+ "<tr>");
    	return header;
    }

    @Override
    public void show() {
        this.mainController.setSubview(new MailListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }
}
