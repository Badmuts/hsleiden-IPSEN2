package Panthera.Controllers;

import java.util.ArrayList;
import java.util.List;

import Panthera.DAO.DebiteurDAO;
import Panthera.Models.AttributeCompare;
import Panthera.Models.Debiteur;
import Panthera.Views.DebiteurenListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Victor
 *
 */
public class DebiteurenController extends Controller {
	
	private DebiteurDAO dao;
	
	public DebiteurenController() {

      try {
          this.dao = new DebiteurDAO();
      } catch (Exception e) {
          e.printStackTrace();
      }
      this.view = new DebiteurenListView(this);
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
			setView(new DebiteurenListView(this)).show();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete unselected,
	 * return lexicographically.
	 */
	public List<Debiteur> filterUnselected(List<Debiteur> debiteuren) {
		List<Debiteur> filtered = new ArrayList<>();
		for(Debiteur debiteur : debiteuren) {
			if(debiteur.isActive()) {
				filtered.add(debiteur);
			}
		}
		filtered.sort(new AttributeCompare());
		return filtered;
	}

}
