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
     *
     * @param mainController
     * @throws Exception
     */
    public FacturenController(MainController mainController) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
        this.facturen = dao.getAllFacturen();
    }

    /**
     *
     * @param mainController
     * @param factuur
     * @throws Exception
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
     * deze methode delegeert naar het dao om alle facturen op te halen uit de database
     * @return
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
     * deze methode delegeert naar de dao om een factuur uit de database te verwijderen
     * er wordt eerst gekeken of de factuur wel is aangevinkt.
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
     *
     * deze methode delegeert naar het dao om een factuur op te slaan
     * en vervolgens terug te keren naar het facturenoverzicht
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
     * deze methode doet een update op de facturen die een nieuwe statu hebben gekregen
     *
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
     * deze methode opent de subview om facturen toe te voegen
     * @throws Exception
     */
    public void cmdShowFactuurAddView() throws Exception{
        mainController.setSubview(new FacturenAddView(this, new Factuur()));
    }

    public void cmdShowFactuurAddView(Factuur factuur) {
        mainController.setSubview(new FacturenAddView(this, factuur));
    }

    public void cmdAddFactuurregel(Factuurregel factuurregel) {

    }

    @Override
    public void show() {
        this.mainController.setSubview(new FacturenListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }


    /**
     *deze methode verzend de factuur naar het lid
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
