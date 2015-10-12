package Panthera.Controllers;

import Panthera.DAO.FactuurDAO;
import Panthera.Models.Factuur;
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
    private FactuurDAO dao;

    public FacturenController(MainController mainController) throws Exception  {
        this.mainController = mainController;
        this.dao = new FactuurDAO();
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
                    Platform.runLater(() -> {
                        facturen.remove(factuur);
                    });
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void cmdUpdateStatus(ObservableList<Factuur> facturen, String status) {
        try {
            for(Factuur factuur: facturen) {
                if(factuur.isBetaald()) {
                    dao.updateStatus(factuur, status);
                    setView(new FacturenListView(this)).show();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void show() {
        this.mainController.setSubview(new FacturenListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }
}
