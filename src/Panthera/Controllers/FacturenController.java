package Panthera.Controllers;

import Panthera.DAO.BestellijstDAO;
import Panthera.DAO.FactuurDAO;
import Panthera.Models.Bestellijst;
import Panthera.Models.Factuur;
import Panthera.Views.FacturenAddView;
import Panthera.Views.FacturenListView;
import Panthera.Views.Viewable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenController extends Controller {

    private FactuurDAO dao;

    public FacturenController() throws Exception  {

        this.dao = new FactuurDAO();
        this.view = new FacturenListView(this);
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

    public void cmdSaveFactuur(Factuur factuur) {
        try {
            dao.save(factuur);
            setView(new FacturenAddView(this)).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
