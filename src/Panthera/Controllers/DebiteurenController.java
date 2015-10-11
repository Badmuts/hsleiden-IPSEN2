package Panthera.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Panthera.DAO.DebiteurDAO;
import Panthera.DAO.EventDAO;
import Panthera.Models.Debiteur;
import Panthera.Models.Event;
import Panthera.Views.DebiteurenListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Victor
 * @author Roy
 *
 */
public class DebiteurenController extends Controller {
	
	private DebiteurDAO dao;
	private EventDAO eventDao;
	
	public DebiteurenController() {

      try {
          this.dao = new DebiteurDAO();
          this.eventDao = new EventDAO();
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
	 * @deprecated
	 * @param debiteuren
	 */
	public void registerEvent(List<Debiteur> debiteuren) {
		try {
			Event event = eventDao.getLastEvent();
			for(Debiteur debiteur : debiteuren) {
				if(debiteur.isActive()) {
					eventDao.setEvent(debiteur, event);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No last event found!");
		}
	}
	
	/**
	 * Set debiteur present at last event.
	 * @param debiteuren
	 */
	public void setPresent(List<Debiteur> debiteuren) {
		try {
			debiteuren = filterPresent(debiteuren);
			Event event = eventDao.getLastEvent();
			for(Debiteur debiteur : debiteuren) {
				eventDao.setEvent(debiteur, event);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Filter not present.
	 */
	public List<Debiteur> filterPresent(List<Debiteur> debiteuren) {
		List<Debiteur> filtered = new ArrayList<>();
		for(Debiteur debiteur : debiteuren) {
			if(debiteur.isPresentBool()) {
				filtered.add(debiteur);
			}
		}
		return filtered;
	}
	
	public void test() {
		System.out.println("test");
	}

}
