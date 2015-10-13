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

    private MainController mainController;
    private ArrayList<Factuur> facturen;
    private FactuurDAO dao;
    private Email email;
    private MailService mailService;

    public FacturenController(MainController mainController) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
//        this.view = new FacturenListView(this);
        this.facturen = dao.getAllFacturen();
    }

    public FacturenController(MainController mainController, Factuur factuur) throws Exception  {
        this.mainController = mainController;
        this.email = new Email();
        this.mailService = new MailService();
        this.dao = new FactuurDAO();
//        this.view = new FacturenListView(this);
        this.facturen = new ArrayList<>();
        this.facturen.add(factuur);
    }

    public FacturenController(MainController mainController, ArrayList<Factuur> facturen) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
//        this.view = new FacturenListView(this);
        this.facturen = facturen;
    }

    public ObservableList<Factuur> cmdGetFacturen() {
        ArrayList<Factuur> facturen = new ArrayList<>();
        try {
           facturen.addAll(dao.getAllFacturen());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(facturen);
    }

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
