package Panthera.Controllers;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Panthera.DAO.DebiteurDAO;
import Panthera.DAO.EventDAO;
import Panthera.Models.AttributeCompare;
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

	private final MainController mainController;
	private DebiteurDAO dao;
	private EventDAO eventDao;
	
	public DebiteurenController(MainController mainController) {
		this.mainController = mainController;

      try {
          this.dao = new DebiteurDAO();
          this.eventDao = new EventDAO();
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
	
	/**
	 * Use setPresent method now.
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
	
	public void setPresent(Debiteur debiteur) {
		try {
			Event event = eventDao.getLastEvent();
			eventDao.setEvent(debiteur, event);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setNotPresent(Debiteur debiteur) {
		Event event;
		try {
			event = eventDao.getLastEvent();
			eventDao.unsetEvent(debiteur, event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Undo setting a Debiteur as Present at last event.
	 * @param debiteuren
	 */
	public void setNotPresent(List<Debiteur> debiteuren) {
		debiteuren = filterPresent(debiteuren);
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

	@Override
	public void show()  {
		this.mainController.setSubview(new DebiteurenListView(this));
	}

    public MainController getMainController() {
        return mainController;
    }
}
