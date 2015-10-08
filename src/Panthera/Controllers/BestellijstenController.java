package Panthera.Controllers;

import Panthera.DAO.BestellijstDAO;
import Panthera.Models.Bestellijst;
import Panthera.Models.Product;
import Panthera.Views.BestellijstenAddView;
import Panthera.Views.BestellijstenSummaryView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles bestelijst logic.
 * @author Roy
 *
 */
public class BestellijstenController extends Controller {
	private MainController mainController;
	private ProductenController productenController;
	private BestellijstDAO dao;
	private Stage stage;
	
	public BestellijstenController(MainController mainController) {
		try {
			this.mainController = mainController;
			this.dao = new BestellijstDAO();
			this.productenController = new ProductenController(this.mainController);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the main menu.
	 */
	public void mainMenu() {
//		setView(new MainMenuView(new MainMenuController()));
	}
	
	public BestellijstenAddView openBestellijstenAddView() {
		return new BestellijstenAddView(this, productenController);
	}
	
	public void print(List<Bestellijst> bestellijsten) {
		
	}
	
	public BestellijstenSummaryView openBestellijstenSummaryView() {
		return new BestellijstenSummaryView(this);
	}
	
	/**
	 * Delete the selected bestellijsten.
	 * @param bestellijsten
	 */
	public void verwijder(List<Bestellijst> bestellijsten) {
		bestellijsten = filterUnselectedBestellijsten(bestellijsten);
		try {
			dao.deleteBestellijsten(bestellijsten);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setView(new BestellijstenSummaryView(this)).show();
	}
	
	/**
	 * Save a new bestellijst.
	 * @param producten
	 */
	public void opslaanBestellijst(List<Product> producten) {
		producten = filterUnselected(producten);
		dao.saveNewBestellijst(producten);
		mainController.setSubview(new BestellijstenSummaryView(this));
	}
	
	/**
	 * Returns a list of all selected products.
	 * @return List<Product>
	 */
	public List<Product> filterUnselected(List<Product> producten) {
		List<Product> filteredList = new ArrayList<>();
		for(Product product : producten) {
			if(product.isActive())
				filteredList.add(product);
		}
		return filteredList;
	}
	
	public List<Bestellijst> filterUnselectedBestellijsten(List<Bestellijst> bestellijsten) {
		List<Bestellijst> filteredList = new ArrayList<>();
		for(Bestellijst bestellijst : bestellijsten) {
			if(bestellijst.isActive()) {
				filteredList.add(bestellijst);
			}
		}
		return filteredList;
	}
	
	/**
	 * Get bestellijsten.
	 * @return
	 */
	public ObservableList<Bestellijst> cmdGetBestellijsten() {
		ArrayList<Bestellijst> bestellijsten = new ArrayList<>();
		try {
			bestellijsten = (ArrayList<Bestellijst>) dao.all();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return FXCollections.observableArrayList(bestellijsten);
	}

	@Override
	public void show() {
		this.mainController.setSubview(new BestellijstenSummaryView(this));
	}

    public MainController getMainController() {
        return mainController;
    }
}
