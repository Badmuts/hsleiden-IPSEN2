package Panthera.Controllers;

import Panthera.DAO.DebiteurDAO;
import Panthera.Models.Debiteur;
import Panthera.Views.DebiteurenListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * 
 * @author Victor
 *
 */
public class DebiteurenController extends Controller {

	private final MainController mainController;
	private DebiteurDAO dao;
	
	public DebiteurenController(MainController mainController) {
		this.mainController = mainController;

      try {
          this.dao = new DebiteurDAO();
      } catch (Exception e) {
          e.printStackTrace();
      }
	}
	
	public ObservableList<Debiteur> cmdGetDebiteuren() {
		ArrayList<Debiteur> debiteuren = new ArrayList<>();
		try {
			debiteuren.addAll(dao.getAllDebiteuren());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(debiteuren);
	}
	public void cmdDeleteDebiteur(ObservableList<Debiteur> debiteuren) {
		try {
			for(Debiteur debiteur: debiteuren) {
				if(debiteur.isActive()) {
					dao.deleteDebiteur(debiteur);
					Platform.runLater(() -> {
						debiteuren.remove(debiteur);
					});
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void cmdAddDebiteur(Debiteur debiteur) {
		try {
			dao.addDebiteur(debiteur);
			mainController.setSubview(new DebiteurenListView(this));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		this.mainController.setSubview(new DebiteurenListView(this));
	}

    public MainController getMainController() {
        return mainController;
    }
}
