package Panthera.Controllers;

import Panthera.DAO.FactuurDAO;
import Panthera.Models.Email;
import Panthera.Models.Factuur;
import Panthera.Models.Factuurregel;
import Panthera.Services.MailService;
import Panthera.Views.FacturenAddView;
import Panthera.Views.FacturenListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenController extends Controller {

    //attributen
    private MainController mainController;
    private ArrayList<Factuur> facturen;
    private FactuurDAO dao;
    private Email email;
    private MailService mailService;

    /**
     * @author Brandon van Wijk
     * Deze constructor wordt gebruikt bij het aanmaken van de subview FacturenListView.
     * Hij krijgt de maincontroller mee omdat die de subviews regelt.
     */
    public FacturenController(MainController mainController) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
        this.facturen = dao.getAllFacturen();
    }

    /**
     * @author Brandon van Wijk
     * Deze constructor wordt gebrukt bij het aanmaken van deze controller in
     * De maincontroller klasse. Hij krijgt ook een factuur object mee
     * Zodat die toegevoegd kan worden aan de facturen lijst in de controller.
     */
    public FacturenController(MainController mainController, Factuur factuur) throws Exception  {
        this.mainController = mainController;
        this.email = new Email();
        this.mailService = new MailService();
        this.dao = new FactuurDAO();
        this.facturen = new ArrayList<>();
        this.facturen.add(factuur);
    }

    public FacturenController(MainController mainController, ArrayList<Factuur> facturen) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
        this.facturen = facturen;
    }

    /**
     * @author Brandon van Wijk
     * Deze methode haalt alle facturen uit de database
     * Doormiddel van het FactuurDAO en stopt deze in een
     * ObservableList die gebruikt wordt in de factuurviews
     * @return Collectie van factuurobjecten
     */
    public ObservableList<Factuur> cmdGetFacturen() {
        ArrayList<Factuur> facturen = new ArrayList<>();
        try {
           facturen.addAll(dao.getAllFacturen());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(facturen);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode krijgt een lijst mee met een factuur of meerdere
     * En kijkt vervolgens of deze aangevinkt staan in de view
     * Zo ja, verwijderd hij deze uit de database via het DAO.
     * @param facturen
     */
    public void cmcDeleteFactuur(ObservableList<Factuur> facturen) {
        try {
            for(Factuur factuur: facturen) {
                if(factuur.isChecked()) {
                    dao.deleteFactuur(factuur);
                    Platform.runLater(() -> facturen.remove(factuur));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Brandon van Wijk
     * Deze methode krijgt een factuur object binnen en slaat deze vervolgens op
     * Via het DAO. Daarna zet hij een nieuwe view zodat je terugkeert naar het factuuroverzicht.
     * @param factuur
     */
    public void cmdSaveFactuur(Factuur factuur) {
        if (factuur.getStatus().equals("")) {
            factuur.setStatus("Concept");
        }
        try {
            dao.save(factuur);
            setView(new FacturenListView(this)).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Brandon van Wijk
     * Deze methode krijgt 1 of meerdere facturen mee en de status
     * Waarnaar de factuur geupdatat moet worden. Er wordt wel eerst
     * Gekeken of de factuur is aangevinkt in het overzicht om te voorkomen dat
     * De status van alle facturen wordt aangepast.
     * @param facturen
     * @param status
     */
    public void cmdUpdateStatus(ObservableList<Factuur> facturen, String status) {
        try {
            for (Factuur factuur : facturen) {
                if (factuur.isChecked()) {
                    dao.updateStatus(factuur, status);
                    setView(new FacturenListView(this)).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Brandon van Wijk
     * Deze methode zet simpelweg de subview om een factuur toe te kunnen voegen
     * @throws Exception
     */
    public void cmdShowFactuurAddView() throws Exception{
        mainController.setSubview(new FacturenAddView(this, new Factuur()));
    }

//    public void cmdShowFactuurAddView(Factuur factuur) {
//        mainController.setSubview(new FacturenAddView(this, factuur));
//    }
//
//    public void cmdAddFactuurregel(Factuurregel factuurregel) {
//
//    }


    /**
     * @author Brandon van Wijk
     * Deze methode zet de subview naar het factuuroverzicht.
     */
    @Override
    public void show() {
        this.mainController.setSubview(new FacturenListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }


    /**
     * @author Brandon van Wijk
     * Deze methode verstuurd de aangevinkte facturen in het overzicht
     * Naar de bijbehorende leden met de factuur als bijlage.
     * @param facturen
     */
    public void cmdSendFactuur(ObservableList<Factuur> facturen) {
        for(Factuur factuur: facturen) {
            if (factuur.isChecked()) {
                email.setTo(factuur.getDebiteur().getEmail());
                //Wordt straks opgehaald uit de organisatie klasse
                email.setFrom("brandonvanwijk@gmail.com");
                email.setSubject("Factuur Benefiet Wijnfestijn");
                email.setText("Hierbij de factuur in de bijlage");
                email.addAttachment(factuur.getPdfPath(), factuur.getPdfPath());
                mailService.send(email);
            }
            cmdUpdateStatus(facturen, "Openstaand");
            setView(new FacturenListView(this)).show();
        }
    }

}
